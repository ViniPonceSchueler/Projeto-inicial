
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO 
{        
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    boolean acessoDB; 
    int status;
    
    public int cadastrarProduto (ProdutosDTO produto)
    {                
        conectaDAO conector = new conectaDAO(); 
        acessoDB = conector.connectDB();
        //acessoDB = new conectaDAO().connectDB();
        
        if (acessoDB == false)
        {
            //JOptionPane.showMessageDialog(null,"Erro de conexão");
            return 0;
        }
        else
        {
            try 
            {                
                prep = conector.conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");                
                prep.setString(1,produto.getNome());
                prep.setInt(2,produto.getValor());
                prep.setString(3, produto.getStatus());
                status = prep.executeUpdate();
                return status; //retornar 1
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro ao gravar: " + ex.getMessage());
                return ex.getErrorCode();
            } 
        }        
    }
    
    
    public ArrayList<ProdutosDTO> listarProdutos()
    {        
        return listagem;
    }       
        
}

