ALTER TABLE oleh.posts ADD CONSTRAINT fk_posts_authors_test FOREIGN KEY (author_id) REFERENCES oleh.authors (id) NOT ENFORCED
ALTER TABLE oleh.posts DROP CONSTRAINT fk_posts_authors_test