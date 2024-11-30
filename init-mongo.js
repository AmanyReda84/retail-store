db = db.getSiblingDB('retailStoreDB');
db.createUser(
    {
        user: "user",
        pwd: "userPassword",
        roles: [
            {
                role: "readWrite",
                db: "retailStoreDB"
            }
        ]
    }
);

db.createCollection('users');
db.createCollection('bills');
//// Create indexes for users
//db.users.createIndex({ "email": 1 });
//
//// Create indexes for bills
//db.bills.createIndex({ "user_id": 1 });
//db.bills.createIndex({ "items.item_id": 1 });

// Insert sample user data
db.users.insertOne({
  _id: "1",
  name: "John Doe",
  email: "john.doe@example.com",
  type: "employee",
  date: ISODate("2024-11-29T00:00:00Z"),
});

db.users.insertOne({
  _id: "2",
  name: "Jane Doe",
  email: "jane.doe@example.com",
  type: "affiliate",
  date: ISODate("2024-11-29T00:00:00Z"),
});

db.users.insertOne({
  _id: "3",
  name: "Sam Smith",
  email: "sam.smith@example.com",
  type: "guest",
  date: ISODate("2024-11-29T00:00:00Z"),
});

db.users.insertOne({
  _id: "4",
  name: "Emily Johnson",
  email: "emily.johnson@example.com",
  type: "another_type",
  date: ISODate("2021-11-29T00:00:00Z"),
});

