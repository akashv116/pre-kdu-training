import { User } from "./q1";


export enum Status {
  Active,
  Inactive,
  Suspended,
}


export type UserStatus = [User, Status];


export function printUserStatus([user, status]: UserStatus): void {
  const statusStr = Status[status]; 
  console.log(`${user.name} is currently ${statusStr}.`);
}
