package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
	@SqlUpdate("create table users (id integer primary key autoincrement, name varchar(100),  email varchar(100), passwdHash varchar(32), salt varchar(32), nbgameWin integer,nbgame integer)")
	void createUserTable();

	@SqlUpdate("insert into users (name,email, passwdHash, salt) values (:name, :email, :passwdHash, :salt)")
	@GetGeneratedKeys
	int insert(@BindBean() User user);

	@SqlQuery("select * from users where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User findByName(@Bind("name") String name);

	@SqlUpdate("drop table if exists users")
	void dropUserTable(); 

	@SqlQuery("select * from users order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<User> all();

	@SqlQuery("select * from users where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findById(@Bind("id") int id);

	@SqlUpdate("update users SET nbgameWin = nbgameWin+1 where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	void incWin(@Bind("id") int id);
	
	@SqlUpdate("update users SET nbgame = nbgame+1 where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	void incGame(@Bind("id") int id);
	
	void close();
}
