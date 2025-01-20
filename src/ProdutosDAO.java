
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;


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
    
    public List<ProdutosDTO> listarProdutos()
    {
        conectaDAO conector = new conectaDAO(); 
        acessoDB = conector.connectDB();        
        
        if (acessoDB == false)
        {
            //JOptionPane.showMessageDialog(null,"Erro de conexão");
            return null;
        }
        else
        {
            try 
            {                
                prep = conector.conn.prepareStatement("SELECT * from produtos");      
                resultset = prep.executeQuery();
                
                List<ProdutosDTO> listaProdutos = new ArrayList<>();
 
                while (resultset.next())    // se encontrou produtos, vamos carregar os dados                
                { 
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(resultset.getInt("id"));
                    produto.setNome(resultset.getString("nome"));
                    produto.setValor(resultset.getInt("valor"));
                    produto.setStatus(resultset.getString("status"));                
                    
                    //Adicionando os elementos na lista criada
                    listaProdutos.add(produto);                      
                }
                
                return listaProdutos;
                
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro ao gravar: " + ex.getMessage());
                return null;
            } 
        }              
    }
    
    public List<ProdutosDTO> listarProdVendidos()
    {
        conectaDAO conector = new conectaDAO(); 
        acessoDB = conector.connectDB();        
        
        if (acessoDB == false)
        {
            //JOptionPane.showMessageDialog(null,"Erro de conexão");
            return null;
        }
        else
        {
            try 
            {                
                prep = conector.conn.prepareStatement("SELECT * FROM produtos WHERE status = 'Vendido'");      
                resultset = prep.executeQuery();
                
                List<ProdutosDTO> listaProdVendidos = new ArrayList<>();
 
                while (resultset.next())    // se encontrou produtos, vamos carregar os dados                
                { 
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(resultset.getInt("id"));
                    produto.setNome(resultset.getString("nome"));
                    produto.setValor(resultset.getInt("valor"));
                    produto.setStatus(resultset.getString("status"));                
                    
                    //Adicionando os elementos na lista criada
                    listaProdVendidos.add(produto);                      
                }
                
                return listaProdVendidos;
                
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro ao gravar: " + ex.getMessage());
                return null;
            } 
        }              
    }    
    
    public int venderProduto(Integer identificador)
    {
        int status;
        conectaDAO conector = new conectaDAO(); 
        acessoDB = conector.connectDB();                
        
        try 
        {
            prep = conector.conn.prepareStatement("UPDATE produtos SET status = 'Vendido' where id = ?");
            prep.setInt(1,identificador);
            status = prep.executeUpdate();
            return status; //retornar 1
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }    
            
}

