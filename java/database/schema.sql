BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS beer;
DROP TABLE IF EXISTS brewery;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE brewery(
        id serial primary key,
        name varchar(200) not null,
        description varchar(500) not null,
        street_address varchar(200) not null,
        city varchar(200) not null,
        state varchar(200) not null,
        zipcode varchar(15) not null,
        vote_count int,
        carousel_count int
);


CREATE TABLE beer(
        id serial primary key,
        name varchar(200) not null,
        type varchar(200) not null,
        description varchar(600) not null,
        abv decimal(3, 1) not null,
        brewery_id bigint,
        
        constraint fk_brewery_id foreign key (brewery_id) references brewery(id)
);

INSERT INTO brewery VALUES (default, 'Seventh Son', 'Seventh Son Brewing has been producing exceptional beers at the intersection of 4th & 4th in Columbus’ historic Italian Village since April of 2013.',
'1101 N. 4th St.', 'Columbus', 'Ohio', '43201', 0, 4);
INSERT INTO brewery VALUES (default, 'Hoof-Hearted', 'Here at Hoof Hearted Brewing we have been turning tanks like cassette tapes since late 2011. Known throughout the industry for our deft hop-wrangling and nude air guitar prowess, we do everything we can to provide fresh, high-quality (and sometimes hi-test) liquid to help you get the party poppin.',
'850 N 4th St.', 'Columbus', 'Ohio', '43215', 0, 4);
INSERT INTO brewery VALUES (default, 'Edison', 'Fresh brews that will leave you needing more.', '785 Science Blvd.', 'Gahanna', 'Ohio', '43230', 0, 4);
INSERT INTO brewery VALUES (default, 'Nostalgia', 'Beer that starts a conversation is the essence of Nostalgia Brewing.', '81 Mill St', 'Gahanna', 'Ohio', '43230', 1, 4);
INSERT INTO brewery VALUES (default, 'North High', 'Beer so good they named a street after us.', '1288 N High St', 'Columbus', 'Ohio', '43201', 0, 4);
INSERT INTO brewery VALUES (default, 'Endeavor', 'There is a story behind every beer.', '909 W 5th Ave', 'Columbus', 'Ohio', '43212', 0, 4);
INSERT INTO brewery VALUES (default, 'Lineage', 'Living a good life is like drinking a good beer. Experience it with all of your senses, enjoy every last drop, and share with friends.', '2971 N. High St.', 'Columbus', 'Ohio', '43202', 0, 4);
INSERT INTO brewery VALUES (default, 'Zaftig Brew Pub', 'Zaftig specializes in high gravity, full bodied beers.', '119 E 5th Ave', 'Columbus', 'Ohio', '43201', 0, 4);
INSERT INTO brewery VALUES (default, 'Land Grant', 'A production craft brewery located in a renovated ex-elevator factory in the historic East Franklinton neighborhood of Columbus, Land-Grant brews a variety of traditional and modern beer styles.', '424 W. Town Street',
'Columbus', 'Ohio', '43215', 0, 4);
INSERT INTO brewery VALUES (default, 'Wolf''s Ridge', 'Passionately Executed. Family owned and proudly self-distributing, we want to pioneer the future of craft in Columbus.', '215 N. 4th Street', 'Columbus', 'Ohio', '43215', 2, 4);
INSERT INTO brewery VALUES (default, 'BrewDog', 'Born in Scotland. Made in Ohio. Our taproom features a premier craft beer restaurant, a dog-friendly expansive patio with a Dog Park, and a Gaming Room.',
'96 Gender Rd.', 'Canal Winchester', 'Ohio', '43110', 1, 4);
INSERT INTO brewery VALUES (default, 'Columbus Brewing', 'Columbus Brewing Company is an independent craft brewery dedicated to exploring the flavors of American hops.', '2555 Harrison Rd.', 'Columbus', 'Ohio', '43204', 0, 4);

INSERT INTO beer VALUES (default, 'Humulus Nimbus', 'Pale Ale', 'A pale golden ale that is both super crisp and super hop forward with a refreshing mouthfeel and a summer friendly 6% abv. Mosaic & simcoe hops lend tart blueberry and fragrant pine to a pleasingly bitter dandelion finish.',
6.0, 1);
INSERT INTO beer VALUES (default, 'Assistant Manager', 'American Golden Ale', 
'The definition of a drinkable and refreshing. Delicate hops and golden malts give way to firm bitterness and a clean finish', 6.0, 1);
INSERT INTO beer VALUES (default, 'Rime', 'Winter IPA', 
'Lightly spiced Winter IPA uses fresh ginger, coriander, and caramelized orange peels', 6.5, 1);
INSERT INTO beer VALUES (default, 'Mr. Owl', 'Brown Ale', 'Double India Brown Ale', 8.5, 1);
INSERT INTO beer VALUES (default, 'Fox in the Stout', 'Stout', 'Salted Chocolate Imperial Stout', 9.7, 1);
INSERT INTO beer VALUES (default, 'Belloq', 'Stout', 'Stout with coffee and vanilla added', 7.5, 2);
INSERT INTO beer VALUES (default, 'Key Bump', 'Triple IPA', 'Have you any idea what the street value of this mountain is? Clean off your mirrored glass table and grab a hundo. Key Bump bout to pack your nose full of those oh-so tropical tones. Passionfruit, guava, pineapples, calamansi and kumquat barrel down the mountain as quick as Alberto Tomba on an Olympic qualifying run. Hopped with the most dangerous weapon in the game (ElMoMoCoe) + fermented w/ the fruity flyboi Orenthal, Key Bump''s the pick-me-up you can''t put down. To quote the great Clyde Von Damme,
"What the hell is Dim Mak?"', 10.5, 2);
INSERT INTO beer VALUES (default, 'Discount Nachos', 'IPA', 'Double Dry-Hopped IPA', 7.3, 2);
INSERT INTO beer VALUES (default, '$60 Nachos', 'IPA', '$60 Nachos? That’s what happens when you let dad outta the house! Wanna drink some ice cold beer? Wayyyyelll Imma letcha! Prime that cheddar-cheese pump w/ the gooiest Citra, Simcoe, & El Dorado.',
8.0, 2);
INSERT INTO beer VALUES (default, 'Club Paradise', 'Sour IPA', 'Our Rotating Tiki Cocktail Inspired Sour IPA brewed with Fruit Purée, Vanilla & Milk Sugar', 8.0, 2);
INSERT INTO beer VALUES (default, 'Nikola', 'Vienna Lager', 'This is a Vienna Lager that has moderate malt sweetness. It has a malt character from Vienna and other colored malts. Balanced with a subtle hop and caramel flavor and aroma. Clean, moderately dry finish.',
5.3, 3);
INSERT INTO beer VALUES (default, 'All Mina', 'Pale Ale', 'A British-Style Pale Ale with a malty middle and light hop aroma and moderate hop flavor. A slightly minerally dry finish.',
4.8, 3);
INSERT INTO beer VALUES (default, 'Gus', 'Porter', 'A classic British style porter with black and brown malt flavor and aroma. Rich body with blanched dry finish.',
5.3, 3);
INSERT INTO beer VALUES (default, 'Willy Goat', 'Bock', 'Malty, with a balanced aroma of Munich, brown and colored malts. Light hop flavor with a dry finish.', 5.1, 3);
INSERT INTO beer VALUES (default, 'Kivi', 'Rye', 'Latvian Honey Rye Lager', 6.0, 3);
INSERT INTO beer VALUES (default, 'Thackery', 'pumpkin', 'Nothing says fall quite like pumpkins and beer', 5.0, 4);
INSERT INTO beer VALUES (default, 'Marty Mc-Fly-PA', 'IPA', 'It doesn''t take 1.21 Gigawatts to taste this delicious New England-style IPA!', 7.0, 4);
INSERT INTO beer VALUES (default, 'Glass Joe', 'IPA', 'You won''t need a Power Glove to attack this East Coast IPA, but it definitely packs a hoppy punch!', 6.0, 4);
INSERT INTO beer VALUES (default, 'The Wraith', 'Stout', 'Before tiger blood, Charlie Sheen had a Dodge M4S Turbo Interceptor. The bad guys never stood a chance. The car was sneaky fast and stealthy, just like this beer. Don''t let the 10% sneak up on you!',
10.0, 4);
INSERT INTO beer VALUES (default, 'Mariachi', 'Mexican Lager', 'No celebration is complete without Mariachi. Grab your band of friends and enjoy! Try it with some jalapeños and lime for a spicy rendition!', 5.6, 4);
INSERT INTO beer VALUES (default, 'Honey Wheat', 'Honey - US',' Honey Wheat is an American wheat ale with 30 pounds of honey added to the brew. Honey Wheat is lightly hopped, slightly sweet, and has a clean finish.', 6.0, 5);
INSERT INTO beer VALUES (default, 'Rise IPA', 'IPA', 'Aggressively hopped and assertively bitter, this crisp, malty India Pale Ale showcases a wonderful bouquet of citrusy and floral aromas.', 6.8, 5);
INSERT INTO beer VALUES (default, 'Filth McNasty', 'Imperial Stout', 'A Russian Imperial Stout with an intensely concentrated roasted malt character. Aggressively hopped to give it a resinous and herbal undertone.', 9.0, 5);
INSERT INTO beer VALUES (default, 'Five', 'IPA', 'Easy drinking dry-hopped pale ale, showcasing aromas of tropical fruit, citrus and herbs. Drinks like a session IPA, but with the body to balance the hops.', 5.5, 5);
INSERT INTO beer VALUES (default, 'Cover Crop', 'Golden Ale', 'A collaboration between the Ohio Farm Bureau and North High Brewing to commemorate the Ohio Farm Bureau’s 100 year anniversary. An easy drinking ale brewed with all Ohio malt and hops.',
4.5, 5);
INSERT INTO beer VALUES (default, 'Holy Ground', 'Export Stout', 'A bigger version of a typical dry Irish stout. This beer is forward with rich, roasted malts. Our’s is nitrogenated for a silky body', 6.0, 6);
INSERT INTO beer VALUES (default, 'New World', 'IPA', 'Hops from three continents with hints of guava, key lime and tropical fruit', 5.5, 6);
INSERT INTO beer VALUES (default, 'Shaka', 'IPA', 'Select hops from three separate continents provide an accentuated aroma of tropical fruit, while keeping bitterness, alcohol content, and malt base balanced.', 6.5, 6);
INSERT INTO beer VALUES (default, 'Landhaus', 'Hefeweizen', 'This is a beer done in the traditional Bavarian style with more than 50% wheat malt and fermented with a yeast that produces strong banana, clove, and vanilla flavors. We add a portion of Munich malt to give a subtle dark crust flavor underneath. Best enjoyed in your finest lederhosen or dirndl.',
5.5, 6);
INSERT INTO beer VALUES (default, 'Altura', 'Latin Lager', 'This beer is dry hopped lightly with New Zealand hops to give the impression of a lime twist in a Mexican lager. The malt base gives complexity while remaining very crisp and crushable. This beer is best enjoyed with company while traversing the mountains of Patagonia.Or at your favorite backyard barbecue.',
5.0, 6);
INSERT INTO beer VALUES (default, 'Spaceship No. 6', 'IPA', 'American IPA', 7.2, 7);
INSERT INTO beer VALUES (default, 'Oscura Obscura', 'Blonde Stout', 'Nitro Blonde Stout', 7.9, 7);
INSERT INTO beer VALUES (default, 'Midnight Sickle', 'Stout', 'Russian Imperial Stout', 10.4, 7);
INSERT INTO beer VALUES (default, 'Monochromatic Moonmist', 'IPA', 'Hazy IPA', 7.0, 7);
INSERT INTO beer VALUES (default, 'Tropical Bernice', 'Berliner Weisse', 'Mango Papaya Berliner Weisse', 4.2, 7);
INSERT INTO beer VALUES (default, 'Mini Bomb Blonde', 'Blonde Ale', 'This spunky little offering proves blondes DO have more fun. Light cereal malts and citrus are the star of this party that go on all night and day.',
5.0, 8);
INSERT INTO beer VALUES (default, 'Hazy Miss Daisy', 'IPA', 'New England IPA', 7.0, 8);
INSERT INTO beer VALUES (default, 'Hop Swapper', 'IPA', 'Double IPA', 10.0, 8);
INSERT INTO beer VALUES (default, 'Too Cans', 'IPA', 'Imperial IPA', 13.0, 8);
INSERT INTO beer VALUES (default, 'Heavy Hearted Amber', 'Amber Ale', 'Amber Ale', 8.0, 8);
INSERT INTO beer VALUES (default, 'Oh, Sure', 'IPA', 'A modern take on a classic. "Oh, Sure" is both the result of constant tinkering, as well as the ethos of all we do at Land-Grant. It is a hybrid of new school juicy and tropical flavor with traditional American IPA craftsmanship makes for an approachable and balanced IPA.',
6.4, 9);
INSERT INTO beer VALUES (default, 'Beard Crumbs', 'Stout', 'Beard Crumbs is a smooth dark stout teeming with notes of chocolate and coffee with a punch of sweetness brought through by the addition of caramelized raisins. It delivers the joy of an oatmeal raisin cookie, minus the crumbly clean up.',
7.4, 9);
INSERT INTO beer VALUES (default, 'Candy Beans', 'IPA', 'This super juicy IPA brings more flavor than a whole bowl of candy beans. We pulled a handful of Belma, Mosaic, Huell Melon, Hallertau Blanc, El Dorado, Calypso, and Bravo hops from the bag. Then we blended them with Madagascar Vanilla to create this candy-like beer with huge blueberry, peach, pear, strawberry, and melon flavor.',
7.3, 9);
INSERT INTO beer VALUES (default, 'Pool Party', 'Pilsner', 'Whether at a five-star resort or in your neighbor''s back yard, no party is complete without the crisp snap of a cold beer. This Pilsner takes its traditional base south of the equator with the addition of Southern Hemisphere hops known for their subtle aromas of tropical fruit and citrus—creating a highly drinkable and refreshing beer.',
5.4, 9);
INSERT INTO beer VALUES (default, 'Blue Hour', 'Sour Ale', 'After sunset, as dusk wanes, and nightfall looms the sky takes on a brilliant cerulean hue creating the photographic phenomenon known as Blue Hour. It provides a calm, tranquil setting perfect for opening up this deliciously tart Blueberry Ale brewed with fresh berries and Mosaic hops.',
6.2, 9);
INSERT INTO beer VALUES (default, 'Daybreak', 'Cream Ale', 'Confusing to the senses in the best way possible. Daybreaks clarity and golden color belie the smooth coffee presence inside. Brewed with local One Line Coffee''s rotating house roast and the finest vanilla beans. ',
5.0, 10);
INSERT INTO beer VALUES (default, 'Pack', 'IPA', 'From our pack to yours, we invite you to share and enjoy this IPA wherever your path leads you.  Brimming with Citra, Mosaic and Amarillo hoppy goodness, Pack is the perfect companion for any and all awaiting',
6.8, 10);
INSERT INTO beer VALUES (default, 'Citrus Surf', 'IPA', 'Fresh grapefruit. lime, and orange zest hang ten with aromatic Citra and Mosaic hops in this delightful summer crusher. A riff on our old favorite Driftwood, this IPA refreshes again and again with its low ABV. Ride the wave to Flavortown!',
4.6, 10);
INSERT INTO beer VALUES (default, 'Royal Nebula', 'IPA', 'A cosmic hazy double IPA hopped with Galaxy, El Dorado, and Citra Hops.', 8.0, 10);
INSERT INTO beer VALUES (default, 'Dire Wolf', 'Stout', ' Dire Wolf is a fiercely bold, dark, and unrelenting. In bringing together exceptional malts from diverse corners of the world, Dire Wolf impresses the palate with a sophisticated blend of dark chocolates, espresso, caramel, bread, and oats.',
10.8, 10);
INSERT INTO beer VALUES (default, 'Elvis Juice', 'IPA', 'An American IPA with a bitter edge that will push your citrus tolerance to the brink and back; Elvis Juice is loaded with tart pithy grapefruit. This IPA has a caramel malt base, supporting a full frontal citrus overload - grapefruit piled on top of intense aroma hops. Waves of crashing pine, orange and grapefruit round out this citrus infused IPA.',
6.5, 11);
INSERT INTO beer VALUES (default, 'Lightspeed', 'IPA', 'Lightspeed is a 4% lo-cal hazy IPA with just 99 calories per can. This light beer has an all-star hop profile featuring Cascade, Centennial, Citra and Nelson Sauvin conjuring flavors of apricot and peach, with hints of citrus fruits and light floral notes refracting off the delicate malt base. Lightspeed proves low calorie does not have to mean low flavor.',
4.0, 11);
INSERT INTO beer VALUES (default, 'Lost Lager', 'Lager', 'Lost Lager is the world''s first carbon negative lager. This mind-blowingly delicious 4.5% German-style Pilsner is lager like it should be, with an extra dose of feel good for the planet.',
4.5, 11);
INSERT INTO beer VALUES (default, 'Cannon Blast', 'Pale Ale', 'The first collaboration beer between BrewDog and Columbus Blue Jackets has hit the ice. A hockey-themed beer that was named by the fans, for the fans. This American Pale Ale delivers a truckload of citrus and pine aromas in a crushable ABV. Our American Pale Ale is crisp in the can, and even crisper off the ice.',
5.0, 11);
INSERT INTO beer VALUES (default, 'Hellcat', 'IPA', 'BrewDog and Iron Maiden unite to launch Hellcat—a feisty 6% ABV India Pale Lager where hops and malts collide. Hellcat rages with a citrus-forward flavor with a no less aggressive malty backbone. The result is a hazy-gold IPL with an epically complex balance.',
6.0, 11);
INSERT INTO beer VALUES (default, 'Bodhi', 'IPA', 'An awakening of the senses by virtue of American hops. Pale golden in color, this double IPA showcases a unique blend of hops, highlighting the true nature of Citra.',
8.3, 12);
INSERT INTO beer VALUES (default, 'Technicolor Hat', 'IPA', 'Technicolor Hat is a citrus-packed IPA that’ll take you on a wild ride through lush citrus groves and tropical oases! Meyer lemon, tangerine and lime are tart on the tongue, but balanced perfectly with tropical notes of passionfruit and mango. Simcoe and Centennial hops bring it all together with a light bitterness that rounds out the journey for a clean, refreshing finish.',
6.9, 12);
INSERT INTO beer VALUES (default, 'Creeper', 'IPA', 'Pale malts provide a stage for the huge hop presence. Creeper is our limited release Imperial IPA featuring an ever evolving blend of American hops.',
10.0, 12);
INSERT INTO beer VALUES (default, 'Sohio Stout', 'Stout', 'This limited release stout is brewed with layers of chocolate, coffee, caramel, and roasted malts to create depth and balance. Generous amounts of locally roasted Brioso coffee beans are added for an intense java aroma.',
8.3, 12);
INSERT INTO beer VALUES (default, 'Citra Noel', 'Holiday Ale', 'Welcome to a new holiday tradition. Our mahogany colored ale features tropical fruit notes from Citra® hops and has a deep rich malt character.',
7.3, 12);

COMMIT TRANSACTION;
