# Expense Tracker API

A RESTful API for tracking personal expenses and income.

## Features

- Add income and expenses
- Categorize transactions
- View transaction history
- Calculate totals by category
- Monthly/yearly reports
- Budget tracking

## API Endpoints

- `POST /api/transactions` - Add transaction
- `GET /api/transactions` - Get all transactions (with filters)
- `GET /api/transactions/{id}` - Get transaction by ID
- `PUT /api/transactions/{id}` - Update transaction
- `DELETE /api/transactions/{id}` - Delete transaction
- `GET /api/transactions/summary` - Get summary (total income, expenses, balance)
- `GET /api/transactions/by-category` - Get totals by category

## Transaction Model

```json
{
  "id": 1,
  "type": "EXPENSE",
  "amount": 50.00,
  "category": "Food",
  "description": "Lunch",
  "date": "2024-01-17",
  "createdAt": "2024-01-17T10:00:00"
}
```

## Categories

- Income: Salary, Freelance, Investment
- Expense: Food, Transport, Entertainment, Bills, Shopping

## Summary Response

```json
{
  "totalIncome": 5000.00,
  "totalExpenses": 2500.00,
  "balance": 2500.00,
  "byCategory": {
    "Food": 500.00,
    "Transport": 300.00,
    "Bills": 1000.00
  }
}
```
