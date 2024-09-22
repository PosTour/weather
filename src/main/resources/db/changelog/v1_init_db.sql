CREATE TABLE user_table (
    id UUID PRIMARY KEY NOT NULL,
    chat_id BIGINT NOT NULL
);

CREATE TABLE phenom (
    id UUID PRIMARY KEY NOT NULL,
    city TEXT NOT NULL ,
    type TEXT NOT NULL,
    user_id UUID REFERENCES user_table(id)
);