package nfse;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
/**
 * @author André Ramos Neves
 */
//Gravar retorno
public class NFSEViaWebService extends JFrame implements ActionListener {
    private JLabel lblArquivo=new JLabel("Escolha o Arquivo");
    private JList list = new JList();
    private JScrollPane pnlList = new JScrollPane();
    private DefaultListModel modelArquivos = new DefaultListModel();
    private JButton cmdTransmitir = new JButton("Transmitir RPS"),cmdFechar = new JButton("Sair");
    private static final String CAMINHO="V:\\nfe\\arquivos\\XMLServico\\Gerado\\EMPRESA\\";
    private final String ARQUIVO="";
    private StringBuilder str = new StringBuilder();
    private String strXML;
    
    public NFSEViaWebService() {
        cmdFechar.addActionListener(this);
        cmdTransmitir.addActionListener(this);
        lblArquivo.setText("Escolha o arquivo...");
        setLayout(new FlowLayout());
        pnlList.setViewportView(list);
        //list.setMinimumSize(new Dimension(getWidth()-40, 40));
        //list.setPreferredSize(new Dimension(getWidth()-40,getHeight()-40));
        
        cmdTransmitir.setSize(80,45);
        cmdFechar.setSize(80,45);
    }
    public static void main(String[]ar){
        NFSEViaWebService nfse = new NFSEViaWebService();
        nfse.setTitle("Transmitir RPS");
        nfse.VerificaPastas();
        nfse.add(nfse.lblArquivo);
        nfse.add(nfse.pnlList);
        nfse.add(nfse.cmdTransmitir);
        nfse.add(nfse.cmdFechar);
        nfse.list.setModel(nfse.modelArquivos);
        nfse.setBounds(500, 300, 300, 220);
        nfse.pnlList.setPreferredSize(new Dimension(nfse.getWidth()-60,nfse.getHeight()-100));
        nfse.setVisible(true);
        nfse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void VerificaPastas(){
        File dir = new File(CAMINHO);  
        String[] children;  
        FilenameFilter filter = new FilenameFilter() {  
            @Override
            public boolean accept(File dir, String name) {  
                return !name.startsWith(".");  
            }  
        };  
        children = dir.list(filter);  
        int i =0;
        for(String arr:children){
            modelArquivos.add(i,children[i]);
            i++;
        }
    }
    private void MontaXML(){
        str.setLength(0);
        str.append("<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:proc=\"http://proces.wsnfe2.dsfnet.com.br\">");
        str.append("<soapenv:Header/><soapenv:Body><proc:testeEnviar soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");
        str.append("<mensagemXml xsi:type=\"xsd:string\"><![CDATA[");
		/* EXEMPLO DE STRING DO XML COMO DEVE SER LIDA*/
        //str.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><ns1:ReqEnvioLoteRPS xmlns:ns1=\"http://localhost:8080/WsNFe2/lote\" xmlns:tipos=\"http://localhost:8080/WsNFe2/tp\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://localhost:8080/WsNFe2/lote http://localhost:8080/WsNFe2/xsd/ReqEnvioLoteRPS.xsd\"><Cabecalho><CodCidade>6291</CodCidade><CPFCNPJRemetente>99999999999999</CPFCNPJRemetente><RazaoSocialRemetente>EMPRESA A ENVIAR</RazaoSocialRemetente><transacao></transacao><dtInicio>2014-09-29</dtInicio><dtFim>2014-09-29</dtFim><QtdRPS>1</QtdRPS><ValorTotalServicos>0.40</ValorTotalServicos><ValorTotalDeducoes>0.00</ValorTotalDeducoes><Versao>1</Versao><MetodoEnvio>DLL</MetodoEnvio><VersaoComponente>1.0.0.7</VersaoComponente></Cabecalho><Lote Id=\"lote:RPS000004\"><RPS Id=\"rps:4\"><Assinatura>7b5c150b531e9546751362e37a69f51c44784d83</Assinatura><InscricaoMunicipalPrestador>ALGUM_NUMERO</InscricaoMunicipalPrestador><RazaoSocialPrestador>EMPRESA</RazaoSocialPrestador><TipoRPS>RPS</TipoRPS><SerieRPS>NF</SerieRPS><NumeroRPS>4</NumeroRPS><DataEmissaoRPS>2014-09-29T09:56:37</DataEmissaoRPS><SituacaoRPS>N</SituacaoRPS><SerieRPSSubstituido></SerieRPSSubstituido><NumeroRPSSubstituido>0</NumeroRPSSubstituido><NumeroNFSeSubstituida>0</NumeroNFSeSubstituida><DataEmissaoNFSeSubstituida>1900-01-01</DataEmissaoNFSeSubstituida><SeriePrestacao>99</SeriePrestacao><InscricaoMunicipalTomador></InscricaoMunicipalTomador><CPFCNPJTomador>99999999999999</CPFCNPJTomador><RazaoSocialTomador>EMPRESA QUE RECEBEU SERVICO</RazaoSocialTomador><TipoLogradouroTomador>RUA</TipoLogradouroTomador><LogradouroTomador>VALPARAIBA</LogradouroTomador><NumeroEnderecoTomador>24</NumeroEnderecoTomador><ComplementoEnderecoTomador></ComplementoEnderecoTomador><TipoBairroTomador>Bairro</TipoBairroTomador><BairroTomador>PARQUE DA FIGUEIRA</BairroTomador><CidadeTomador>6291</CidadeTomador><CidadeTomadorDescricao>CAMPINAS</CidadeTomadorDescricao><CEPTomador>99999999</CEPTomador><EmailTomador>EMAILOCULTO@terra.com.br</EmailTomador><CodigoAtividade>000121101</CodigoAtividade><AliquotaAtividade>00.002</AliquotaAtividade><TipoRecolhimento>A</TipoRecolhimento><MunicipioPrestacao>6291</MunicipioPrestacao><MunicipioPrestacaoDescricao>Campinas</MunicipioPrestacaoDescricao><Operacao>A</Operacao><Tributacao>T</Tributacao><ValorPIS>0.00</ValorPIS><ValorCOFINS>0.00</ValorCOFINS><ValorINSS>0.00</ValorINSS><ValorIR>0.00</ValorIR><ValorCSLL>0.00</ValorCSLL><AliquotaPIS>0.00</AliquotaPIS><AliquotaCOFINS>0.00</AliquotaCOFINS><AliquotaINSS>0.00</AliquotaINSS><AliquotaIR>0.00</AliquotaIR><AliquotaCSLL>0.00</AliquotaCSLL><DescricaoRPS>RPS PARA TESTE DE TRANSMISSAO</DescricaoRPS><DDDPrestador>019</DDDPrestador><TelefonePrestador>99999999</TelefonePrestador><DDDTomador></DDDTomador><TelefoneTomador></TelefoneTomador><Itens><Item><DiscriminacaoServico>TESTE DE ITEM DE RPS</DiscriminacaoServico><Quantidade>1.0000</Quantidade><ValorUnitario>0.4000</ValorUnitario><ValorTotal>0.40</ValorTotal><Tributavel>S</Tributavel></Item></Itens></RPS></Lote></ns1:ReqEnvioLoteRPS>");
        str.append(strXML);
        str.append("]]>");
	str.append("</mensagemXml>");
	str.append("</proc:testeEnviar>");
	str.append("</soapenv:Body>");
	str.append("</soapenv:Envelope>");        
    }
    public void LeArquivoXML(String Caminho){
        FileReader read = null;
        try {
            strXML="";
            Document doc = null;
            read = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(read);
            String linha = lerArq.readLine(); 
            // lê a primeira linha a variável "linha" recebe o valor "null" quando o processo de repetição atingir o final do arquivo texto
            while (linha != null) {
                strXML=linha;
                linha = lerArq.readLine();
            }
        } catch (IOException e) {
            Logger.getLogger(NFSEViaWebService.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                read.close();
            } catch (IOException ex) {
                Logger.getLogger(NFSEViaWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String TesteEnviar(){
        try {
            MontaXML();
            URL url = new URL("http://issdigital.campinas.sp.gov.br/WsNFe2/LoteRps.jws?wsdl");
               HttpURLConnection urlconn;
               //urlconn.
               urlconn = (HttpURLConnection) url.openConnection();
               // Preparar cabeçalho do HTTP Request
               urlconn.setRequestMethod("POST");
               urlconn.setRequestProperty("Content-Type","text/xml; charset=utf-8");
               urlconn.addRequestProperty("SOAPAction","\"http://issdigital.campinas.sp.gov.br/WsNFe2/LoteRps.jws?wsdl\"");
               urlconn.setDoOutput(true);
               
               //Criar conteúdo do HTTP Request que será enviado em Bytes, normalmente é melhor serializar ele.
               OutputStream out = urlconn.getOutputStream();
               for(int i=0; i<str.length(); i++)
                   out.write(str.charAt(i));
               
               // Criar objeto DOM XML para a navegação do SOAP de resposta... 
                Document doc =null;
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();    
                
                //LER HTTP RESPONSE
                InputStream connInputStream = urlconn.getInputStream(); 

              // Processar XML de retorno...

                try {
                   doc = db.parse(connInputStream);
                } catch (SAXException ex) {
                    System.out.println("FALHA NA COMUNICAÇÃO");
                    Logger.getLogger(NFSEViaWebService.class.getName()).log(Level.SEVERE, null, ex);
                    return doc.getDocumentElement().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNodeValue(); 
                }
                  return doc.getDocumentElement().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNodeValue(); 
        } catch (ParserConfigurationException | IOException ex) {
            Logger.getLogger(NFSEViaWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "0|FALHA|FALHA NO WEBSERVICE,XML NAO GERADO";
        } 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==cmdFechar)
           System.exit(0);
       else
          if(list.getSelectedValue()==null)
            JOptionPane.showMessageDialog(rootPane, "Selecione um arquivo!");
          else{
            LeArquivoXML(CAMINHO + (list.getSelectedValue().toString()));
            System.out.println(TesteEnviar());
          }
    }
}
