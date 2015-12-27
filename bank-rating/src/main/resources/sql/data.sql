INSERT INTO fetch_data_status(id, created, modified, name) VALUES( 1, NOW(), NOW(), 'new' );
INSERT INTO fetch_data_status(id, created, modified, name) VALUES( 2, NOW(), NOW(), 'running' );
INSERT INTO fetch_data_status(id, created, modified, name) VALUES( 3, NOW(), NOW(), 'ok' );
INSERT INTO fetch_data_status(id, created, modified, name) VALUES( 4, NOW(), NOW(), 'failure' );

INSERT INTO fetch_data(created,modified,fetch_status_id,url) VALUES(NOW(),NOW(),1,'http://minfin.com.ua/banks/rating/');