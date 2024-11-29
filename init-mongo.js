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
  _id: "user1",
  name: "John Doe",
  email: "john.doe@example.com",
  type: "employee",
  date: ISODate("2024-11-29T00:00:00Z"),
});

// Insert sample bill data
db.bills.insertOne({
  _id: "bill1",
  user_id: "user1",
  date: ISODate("2024-11-29T00:00:00Z"),
  total: 150.00,
  discount: 20.00,
  total_after_discount: 130.00,
  items: [
    {
      item_id: "item1",
      name: "Product 1",
      quantity: 2,
      price: 50.00
    },
    {
      item_id: "item2",
      name: "Product 2",
      quantity: 1,
      price: 50.00
    }
  ]
});