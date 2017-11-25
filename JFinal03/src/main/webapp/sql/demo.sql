#namespace("user")
  #sql ("userList")
    SELECT * FROM user order by id desc
  #end

  #sql ("useridAnd1")
    SELECT * FROM user where id = ?
  #end

  #sql ("userid2And7")
    SELECT * FROM user where id between ? and ?
  #end

  #sql ("userid2And7New")
    SELECT * FROM user where id between #p(min_1) and #p(min_2)
  #end

  #sql ("userUpdate")
    Update user set name = ? where id = ?
  #end

  #sql("userDelete")
    delete from user where id =?
  #end

  #sql("userS")
    select * from user where id = #p(idi)
  #end

  #sql("one")
    select a.*,b.uid from user a,hobby b where a.id = b.uid
  #end

  #sql("UserName")
      select * from user where name like ?
    #end



#end
