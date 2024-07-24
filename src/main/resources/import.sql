INSERT INTO users (name, surname, mail, password, funds) VALUES ('John', 'Doe', 'jdoe@email.com', '$2a$12$Z271Idp8yY89vfXjNEb8ceUJA1iXdTrepPH2kbOA7D8RRjwtYa8Eq', 10000.0);

INSERT INTO campaigns (name, keywords, bid_amount, campaign_fund, status, town, radius, user_id) VALUES ('Store Campaign', 'CAMPAIGN, STORE, JEWELERY', 1.5, 3000.0, true, 'London', 10, 1);
INSERT INTO campaigns (name, keywords, bid_amount, campaign_fund, status, town, radius, user_id) VALUES ('Health Campaign', 'CAMPAIGN, HEALTH', 5.0, 12000.0, false, 'Manchester', 20, 1);