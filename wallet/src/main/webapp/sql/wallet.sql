#namespace("wallet")

    #sql("all")
      select * from pay where present = 1
    #end

#end