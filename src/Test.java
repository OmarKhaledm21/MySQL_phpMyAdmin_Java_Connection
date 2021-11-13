import java.sql.*;

public class Test {
    public static void connection(){
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost/students?serverTimezone=UTC",
                    "omar",
                    "******"
            );

            System.out.println("Connected!");
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = s.executeQuery("SELECT id, fname, lname, city FROM info");
            //To show data about table
            ResultSetMetaData data = res.getMetaData();

            System.out.println(data.getColumnName(1));

            res.last();
            System.out.println("Number of rows: "+res.getRow()+"\nNumber of columns: "+data.getColumnCount());


            for(int i=1; i<= data.getColumnCount(); i++){
                System.out.print(String.format(
                    "%-10s",
                    data.getColumnName(i)+" "
                ));
            }
            System.out.println();

            for(res.first(); !res.isAfterLast(); res.next()){

                System.out.println(String.format("%-9s %-10s %-10s %-10s"
                        ,res.getString("id"),res.getString("fname"),res.getString("lname"),"City: "+res.getString("city")));

            }
            if(res.isAfterLast()){
                res.first();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


/*
            r is res
            r.next(); -> one step forward    r.previous(); -> one step back   r.absolute(4); -> moves cursor forward   r.relative(-1); -> moves cursor backwards
            r.first();  r.beforeFirst();  r.last();  r.beforeLast();
            r.isFirst();  r.isBeforeFirst();  r.isLast();  r.isAfterLast();
             */
            /*
            while (res.next()){
                System.out.println(res.getString("fname")+" "+res.getString("lname")+"   City: "+res.getString("city"));
            }
            if(res.getRow()==4){
                    res.updateInt("id",5);
                    res.updateString("fname","David");
                    res.updateRow();
             }
            */


//Code to add a row to db
//res.moveToInsertRow();
//res.updateInt("id",6);
//res.updateString("fname","Jessy");
//res.updateString("lname","Midors");
//res.updateString("city","Moscow");
//res.insertRow();
//res.deleteRow();
