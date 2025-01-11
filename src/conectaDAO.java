
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

        
        
public class conectaDAO 
{
    
    Connection conn = null;   //criando um objeto do tipo connection chamado conn

    public String url = "jdbc:mysql://localhost:3306/senac_ead"; //Nome da base de dados
    public String user = "root"; //nome do usuário do MySQL
    public String password = "r32Nit06$"; //senha do MySQL 
        
    public boolean connectDB()
    {        
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            // System.out.println("Conexão realizada com sucesso");
                        
            return true;
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            //JOptionPane.showMessageDialog(null, "Falha na conexão com o banco " + ex.getMessage());
            return false;
        }       
        // return conn;
    }
    
        
    public void disconnectDB()
    {
        try 
        {
            if (conn != null && !conn.isClosed()) 
            {
                conn.close();
                // System.out.println("Voce se desconectou do banco de dados.");
            }
        }        
        catch (SQLException ex) 
        {
             System.out.println("Nao foi possivel desconectar do banco dados.");
        }
    }    
    
}
