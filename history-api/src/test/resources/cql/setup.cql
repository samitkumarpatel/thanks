CREATE TABLE IF NOT EXISTS history(
  id uuid PRIMARY KEY,
  member_uuid uuid,
  point int,
  created_date timestamp
);