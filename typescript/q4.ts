import { User } from "./q1";

export function findById(
  users: User[],
  id: number,
  throwOnError: boolean = false
): User | undefined | never {
  const user = users.find((user) => user.id === id);

  if (!user && throwOnError) {
    throw new Error(`User with ID ${id} not found`);
  }

  return user;
}
