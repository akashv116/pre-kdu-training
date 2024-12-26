package com.prekdu;

import java.util.*;

// Enums to represent statuses, membership types, and resource properties
enum AvailabilityStatus {
  AVAILABLE,
  BORROWED,
  RESERVED
}

enum MembershipType {
  STANDARD,
  PREMIUM
}

enum Frequency {
  WEEKLY,
  MONTHLY
}

enum Format {
  PDF,
  EPUB
}

// Custom exception to handle resource availability
class ResourceNotAvailableException extends Exception {
  public ResourceNotAvailableException(String message) {
    super(message);
  }
}

// Custom exception to handle maximum loan limits
class MaximumLoanExceededException extends Exception {
  public MaximumLoanExceededException(String message) {
    super(message);
  }
}

// Custom exception to handle invalid membership types
class InvalidMembershipException extends Exception {
  public InvalidMembershipException(String message) {
    super(message);
  }
}

// Abstract class for common library resource attributes and behavior
abstract class LibraryResource {
  protected String resourceId; // Unique identifier for the resource
  protected String title; // Title of the resource
  protected AvailabilityStatus
      availabilityStatus; // Status of the resource (Available, Borrowed, Reserved)

  // Constructor to initialize common resource fields
  public LibraryResource(String resourceId, String title) {
    this.resourceId = resourceId;
    this.title = title;
    this.availabilityStatus = AvailabilityStatus.AVAILABLE; // Default status
  }

  // Abstract method to calculate late fee
  public abstract double calculateLateFee(int daysLate);

  // Abstract method to get the maximum loan period
  public abstract int getMaxLoanPeriod();
}

// Interface for resources that can be renewed
interface Renewable {
  boolean renewLoan(LibraryMember member);
}

// Interface for resources that can be reserved
interface Reservable {
  void reserve(LibraryMember member) throws ResourceNotAvailableException;

  void cancelReservation(LibraryMember member);
}

// Implementation of Book as a LibraryResource
class Book extends LibraryResource implements Renewable, Reservable {
  private String author; // Author of the book
  private String isbn; // ISBN number of the book

  // Constructor to initialize book-specific fields
  public Book(String resourceId, String title, String author, String isbn) {
    super(resourceId, title);
    this.author = author;
    this.isbn = isbn;
  }

  // Late fee calculation for books (e.g., $0.5 per day)
  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.5;
  }

  // Maximum loan period for books (e.g., 14 days)
  @Override
  public int getMaxLoanPeriod() {
    return 14;
  }

  // Renew loan if the book is currently borrowed
  @Override
  public boolean renewLoan(LibraryMember member) {
    if (availabilityStatus == AvailabilityStatus.BORROWED) {
      availabilityStatus = AvailabilityStatus.RESERVED; // Update status to reserved after renewal
      return true;
    }
    return false;
  }

  // Reserve the book if it is available
  @Override
  public void reserve(LibraryMember member) throws ResourceNotAvailableException {
    if (availabilityStatus == AvailabilityStatus.AVAILABLE) {
      availabilityStatus = AvailabilityStatus.RESERVED;
    } else {
      throw new ResourceNotAvailableException("Book is not available for reservation.");
    }
  }

  // Cancel reservation for the book
  @Override
  public void cancelReservation(LibraryMember member) {
    availabilityStatus = AvailabilityStatus.AVAILABLE;
  }
}

// Implementation of DigitalContent as a LibraryResource
class DigitalContent extends LibraryResource implements Renewable {
  private double fileSize; // File size in MB
  private Format format; // Format of the digital content (PDF, EPUB)

  // Constructor to initialize digital content-specific fields
  public DigitalContent(String resourceId, String title, double fileSize, Format format) {
    super(resourceId, title);
    this.fileSize = fileSize;
    this.format = format;
  }

  // Late fee calculation for digital content (e.g., $0.2 per day)
  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.2;
  }

  // Maximum loan period for digital content (e.g., 7 days)
  @Override
  public int getMaxLoanPeriod() {
    return 7;
  }

  // Renew loan if the digital content is currently borrowed
  @Override
  public boolean renewLoan(LibraryMember member) {
    return availabilityStatus == AvailabilityStatus.BORROWED;
  }
}

// Implementation of Periodical as a LibraryResource
class Periodical extends LibraryResource implements Renewable, Reservable {
  private int issueNumber; // Issue number of the periodical
  private Frequency frequency; // Frequency of publication (Weekly, Monthly)

  // Constructor to initialize periodical-specific fields
  public Periodical(String resourceId, String title, int issueNumber, Frequency frequency) {
    super(resourceId, title);
    this.issueNumber = issueNumber;
    this.frequency = frequency;
  }

  // Late fee calculation for periodicals (e.g., $0.3 per day)
  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.3;
  }

  // Maximum loan period for periodicals (e.g., 7 days)
  @Override
  public int getMaxLoanPeriod() {
    return 7;
  }

  // Renew loan if the periodical is currently borrowed
  @Override
  public boolean renewLoan(LibraryMember member) {
    if (availabilityStatus == AvailabilityStatus.BORROWED) {
      availabilityStatus = AvailabilityStatus.RESERVED;
      return true;
    }
    return false;
  }

  // Reserve the periodical if it is available
  @Override
  public void reserve(LibraryMember member) throws ResourceNotAvailableException {
    if (availabilityStatus == AvailabilityStatus.AVAILABLE) {
      availabilityStatus = AvailabilityStatus.RESERVED;
    } else {
      throw new ResourceNotAvailableException("Periodical is not available for reservation.");
    }
  }

  // Cancel reservation for the periodical
  @Override
  public void cancelReservation(LibraryMember member) {
    availabilityStatus = AvailabilityStatus.AVAILABLE;
  }
}

// Class representing a library member
class LibraryMember {
  private String memberId; // Unique identifier for the member
  private MembershipType membershipType; // Type of membership (Standard, Premium)
  private List<LibraryResource> borrowedResources; // List of borrowed resources

  // Constructor to initialize member fields
  public LibraryMember(String memberId, MembershipType membershipType)
      throws InvalidMembershipException {
    if (membershipType == null) {
      throw new InvalidMembershipException("Invalid membership type.");
    }
    this.memberId = memberId;
    this.membershipType = membershipType;
    this.borrowedResources = new ArrayList<>();
  }

  // Borrow a resource with validation for availability and loan limits
  public void borrowResource(LibraryResource resource)
      throws ResourceNotAvailableException, MaximumLoanExceededException {
    if (resource.availabilityStatus != AvailabilityStatus.AVAILABLE) {
      throw new ResourceNotAvailableException("Resource is not available.");
    }

    int maxLoanLimit = membershipType == MembershipType.PREMIUM ? 10 : 5;
    if (borrowedResources.size() >= maxLoanLimit) {
      throw new MaximumLoanExceededException("Maximum loan limit exceeded.");
    }

    borrowedResources.add(resource);
    resource.availabilityStatus = AvailabilityStatus.BORROWED;
  }

  // Return a resource and update its status
  public void returnResource(LibraryResource resource) {
    borrowedResources.remove(resource);
    resource.availabilityStatus = AvailabilityStatus.AVAILABLE;
  }

  // Getter for membership type
  public MembershipType getMembershipType() {
    return membershipType;
  }

  // List borrowed resources
  public void listBorrowedResources() {
    if (borrowedResources.isEmpty()) {
      System.out.println("No resources borrowed.");
    } else {
      System.out.println("Borrowed Resources:");
      for (LibraryResource resource : borrowedResources) {
        System.out.println("- " + resource.title);
      }
    }
  }
}

// Main class for testing the system
public class LibraryManagementSystem {
  public static void main(String[] args) {
    try {
      // Create a library member
      LibraryMember member = new LibraryMember("M001", MembershipType.STANDARD);

      // Create a book, a periodical, and a digital content, and perform borrowing and returning
      // actions
      Book book = new Book("B001", "Java Programming", "Author A", "1234567890");
      Periodical periodical = new Periodical("P001", "Tech Weekly", 42, Frequency.WEEKLY);

      // Borrow the book
      member.borrowResource(book);
      System.out.println("Borrowed: " + book.title);

      // List borrowed resources
      member.listBorrowedResources();

      // Attempt to borrow the periodical
      member.borrowResource(periodical);
      System.out.println("Borrowed: " + periodical.title);

      // List borrowed resources again
      member.listBorrowedResources();

      // Return the book
      member.returnResource(book);
      System.out.println("Returned: " + book.title);

      // List borrowed resources after return
      member.listBorrowedResources();

      // Return the periodical
      member.returnResource(periodical);
      System.out.println("Returned: " + periodical.title);

      // List borrowed resources after both returns
      member.listBorrowedResources();

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
