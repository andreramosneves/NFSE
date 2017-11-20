/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nfse;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ANDRE RAMOS NEVES
 */
public class NFSE {

    
    
    public static void main(String[] args) {
            int Erro;
            //Declarações da 1ª Etapa do Envio da Remessa

            int CodCidade;
            String CPFCNPJRemetente;
            String RazaoSocialRemetente;
            int QtdRPS;
            double ValorTotalServicos;
            double ValorTotalDeducoes;

            //Declarações da 2ª Etapa do Envio da Remessa

            String InscricaoMunicipalPrestador;
            String RazaoSocialPrestador;
            String TipoRPS;
            String SerieRPS;
            int NumeroRPS;
            String DataEmissaoRPS;
            String SituacaoRPS;
            String SerieRPSSubstituido;
            int NumeroRPSSubstituido;
            int NumeroNFSeSubstituida;
            String DataEmissaoNFSeSubstituida;
            String SeriePrestacao;
            String InscricaoMunicipalTomador;
            String CPFCNPJTomador;
            String RazaoSocialTomador;
            String DocTomadorEstrangeiro;
            String TipoLogradouroTomador;
            String LogradouroTomador;
            String NumeroEnderecoTomador;
            String ComplementoEnderecoTomador;
            String TipoBairroTomador;
            String BairroTomador;
            int CidadeTomador;
            String CidadeTomadorDescricao;
            String CEPTomador;
            String EmailTomador;
            String CodigoAtividade;
            double AliquotaAtividade;
            String TipoRecolhimento;
            int MunicipioPrestacao;
            String MunicipioPrestacaoDescricao;
            String Operacao;
            String Tributacao;
            double ValorPIS;
            double ValorCOFINS;
            double ValorINSS;
            double ValorIR;
            double ValorCSLL;
            double AliquotaPIS;
            double AliquotaCOFINS;
            double AliquotaINSS;
            double AliquotaIR;
            double AliquotaCSLL;
            String DescricaoRPS;
            String DDDPrestador;
            String TelefonePrestador;
            String DDDTomador;
            String TelefoneTomador;
            String MotCancelamento;
            String CpfCnpjIntermediario;
            //Quantidade de Servicos e Deduçôes do RPS
            int QtdServicos;
            int QtdDeducoes;

            //Declarações da 3ª Etapa : Itens do RPS
            String DiscriminacaoServico;
            double Quantidade;
            double ValorUnitario;
            double ValorTotal;
            String Tributavel;

            //Declarações da 3ª Etapa : Deduções do RPS
            String DeducaoPor;
            String TipoDeducao;
            String CPFCNPJReferencia;
            int NumeroNFReferencia;
            double ValorTotalReferencia;
            double PercentualDeduzir;
            double ValorDeduzir;

            int i, j;

            //Declarações necessarias pare envio e consulta de lote
            String Assincrono = "";
            String Sucesso = "";
            int NumeroLote = 0;

            int QtdNotasProcessadas = 0;

            //Declarações dos Erros e Alertas de Retorno
            int Erros, Alertas;



            int CodErro = 0;
            String Descricao = "";
            String InscricaoPrestador = "";

            //Declarações necessárias para a Consulta de Nota
            int NumeroNFe = 0;
            String CodigoVerificacao = "";

            String DocAssinatura;
        
     //System.loadLibrary("rbjrNFe20");
     //System.load("C:\\Windows\\System32\\rbjrNFe20.dll");
     //System.setProperty("jna.library.path","C:\\Windows\\System32\\msvcrt20.dll");
 //    System.getenv().   
      //CupomFiscalMetodos INSTANCE = (CupomFiscalMetodos) Native.loadLibrary("rbjrNFe20", CupomFiscalMetodos.class);
      //System.out.println(System.getProperties().elements().nextElement());
        ServicosNFse dll = ServicosNFse.INSTANCE;
            String strLink1="http://issdigital.campinas.sp.gov.br/WsNFe2/LoteRps.jws";
            String strLink2="http://issdigital.campinas.sp.gov.br/WsNFe2/LoteRps.jws?wsdl";
        
        //Erro = dll.setURL("http://issdigital.campinas.sp.gov.br/WsNFe2/LoteRps.jws",
//"http://issdigital.campinas.sp.gov.br/WsNFe2/LoteRps.jws?wsdl");
        //Homologação
           // System.out.println(dll.ObterErroInterno(Erro));
               
        
                    CodCidade=6291;
                    CPFCNPJRemetente="99999999999999";
                    RazaoSocialPrestador = "RAZAO SOCIAL NAO MOSTRADA   ";
        dll.CriarLote(CodCidade, CPFCNPJRemetente,RazaoSocialPrestador);
                    InscricaoMunicipalPrestador = "0";
                    RazaoSocialPrestador = "EMPRESA MODELO";

                    TipoRPS = "RPS"; //Tipo RPS - Padrão 'RPS'
                    SerieRPS = "NF"; //SerieRPS - Padrão 'NF'
                    NumeroRPS = 1; //Número Sequencial de Rps - Para buscar o numero do ultimo RPS convertido usar a função Consulta Sequêncial RPS
                    //Obs: A numeração do RPS deve ser sequêncial, ou seja se o último RPS for o 11, o próximo deve ser o 12 ..., deve ser adionado em ordem por numeração no lote.
                    DataEmissaoRPS = "03/03/2010 15:30:00";   //Sempre deve estar formatado com 'DD/MM/YYYY HH:MM:SS' COM DATA E HORA.
                    SituacaoRPS = "N"; // N- Normal C-Cancelado
                    SerieRPSSubstituido = "";  //Campo não obrigatório
                    NumeroRPSSubstituido = 0; //Campo não obrigatório informar zero
                    NumeroNFSeSubstituida = 0; //Campo não obrigatorio informar zero
                    DataEmissaoNFSeSubstituida = "01/01/1900"; //Campo não obrigatório. Deve ser informado '01/01/1900' quando não for informar nenhum dado.
                    SeriePrestacao = "99"; //Série de prestação informar 99 para Modelo Único
                    InscricaoMunicipalTomador = "000000000";  //Inscrição Municipal do Tomador, quando não tiver informar vazio
                    CPFCNPJTomador = "00000000191"; //CPF ou CNPJ do Tomador, campo obrigatório
                    RazaoSocialTomador = "EMPRESA DE TESTES";
                    DocTomadorEstrangeiro = "";//Documento de identificação de tomador estrangeiro. Se nao tive deixar vazio.
                    TipoLogradouroTomador = "RUA";//Tipo do Logradouro Ex.: Rua, Avenida, Travessa, Bloco etc.).
                    LogradouroTomador = "SETE DE SETEMBRO";
                    NumeroEnderecoTomador = "335";
                    ComplementoEnderecoTomador = "";
                    TipoBairroTomador = "Bairro"; //Tipo de Bairro do Tomador. Padrão 'Bairro'
                    BairroTomador = "CENTRO";
                    CidadeTomador = 6291; //Código da cidade do tomador padrão SIAFI  Ex.: 1219-Teresina, 0427-Belem
                    CidadeTomadorDescricao = "CAMPINAS"; //Descrição da cidade do tomador
                    CEPTomador = "64001210";
                    EmailTomador = "res@bol.com.br"; //Email do tomador, campo obrigatório. Caso não tiver email informar '-', caso queira informar mais de um email colocar ponto-virgula entre os email e no final EX: res@bol.com.br;outro@bol.com.br;
                    CodigoAtividade = "829979900";//Código CNAE da ativida vinculada ao prestador, informada pela Prefeitura.
                    AliquotaAtividade = 5.00;   //Alíquota de ISS da atividade.
                    TipoRecolhimento = "A"; //Tipo de Recolhimento A-A Recolher , R-Retido na fonte.
                    MunicipioPrestacao = 6291; //Código do município de prestação padrão SIAFI  Ex.: 1219-Teresina, 0427-Belem, 5403-Uberlandia, 6291-Campinas
                    MunicipioPrestacaoDescricao = "CAMPINAS";
                    Operacao = "A"; // Opções de Operação : A-Sem Dedução, B-Com Dedução/Materiais, C-Imune/Isenta de ISSQN, D-Devolução/Simples Remessa, G-Construção Civil, H-Regime Estimativa e I-Sociedade Civil }
                    Tributacao = "T"; //  Opções de Tributação : C - Isenta de ISS, E - Não Incidência no Município, F  - Imune, K - Exigibilidd Susp.Dec.J/Proc.A, N - Não Tributável, T – Tributável, G - Tributável Fixo, H  - Tributável S.N.
                    ValorPIS = 0;
                    ValorCOFINS = 0;
                    ValorINSS = 0;
                    ValorIR = 0;
                    ValorCSLL = 0;
                    AliquotaPIS = 0;
                    AliquotaCOFINS = 0;
                    AliquotaINSS = 0;
                    AliquotaIR = 0;
                    AliquotaCSLL = 0;
                    DescricaoRPS = "RPS enviado em teste"; //Descrição do RPS
                    DDDPrestador = "011"; //DDD do Prestador, campo obrigatório deve ter 3 digitos
                    TelefonePrestador = "80804040";//Telefone do Prestador, campo obrigatório deve ter 8 digitos
                    DDDTomador = "011";//DDD do Tomador, campo obrigatório deve ter 3 digitos
                    TelefoneTomador = "20203030";//Telefone do Tomador, campo obrigatório deve ter 8 digitos
                    MotCancelamento = "";//Motivo do cancelamento, obrigatório caso o RPS for situação C-Cancelado
                    CpfCnpjIntermediario = ""; //Cpf ou Cnpj do Intermediario caso o tipo de Operação for J-Intermediação. Obs: Essa operação é usada apenas na cidade de Campo Grande-MS


                    //A função abaixo inclui o RPS na lista de RPS do lote, passando os dados que foram setado anteriormente.
                    Erro = dll.AdicionarRPSV1(InscricaoMunicipalPrestador, RazaoSocialPrestador, TipoRPS, SerieRPS,
                                        NumeroRPS, DataEmissaoRPS, SituacaoRPS, SerieRPSSubstituido,
                                        NumeroRPSSubstituido, NumeroNFSeSubstituida, DataEmissaoNFSeSubstituida,
                                        SeriePrestacao, InscricaoMunicipalTomador, CPFCNPJTomador,
                                        RazaoSocialTomador, DocTomadorEstrangeiro, TipoLogradouroTomador, LogradouroTomador,
                                        NumeroEnderecoTomador, ComplementoEnderecoTomador, TipoBairroTomador, BairroTomador,
                                        CidadeTomador, CidadeTomadorDescricao, CEPTomador,
                                        EmailTomador, CodigoAtividade, AliquotaAtividade, TipoRecolhimento,
                                        MunicipioPrestacao, MunicipioPrestacaoDescricao, Operacao,
                                        Tributacao, ValorPIS, ValorCOFINS, ValorINSS,
                                        ValorIR, ValorCSLL, AliquotaPIS, AliquotaCOFINS,
                                        AliquotaINSS, AliquotaIR, AliquotaCSLL, DescricaoRPS,
                                        DDDPrestador, TelefonePrestador, DDDTomador, TelefoneTomador,
                                        MotCancelamento,
                                        CpfCnpjIntermediario);  

                    if(Erro !=0)
                        JOptionPane.showMessageDialog(null, "Erro ao Adicionar");
                    DiscriminacaoServico = "Descricao do Servico ...";
                    Quantidade = 1.5555;
                    ValorUnitario = 155.5555;
                    Tributavel = "S";
                    Erro = dll.AdicionarItemServicoRPS(DiscriminacaoServico, Quantidade, ValorUnitario, Tributavel);
                    if(Erro !=0)
                        JOptionPane.showMessageDialog(null, "Erro ao Adicionar Itens");
                 
                        DeducaoPor = "Valor";//A dedução pode ser por 'Valor' ou por 'Percentual'
                        TipoDeducao = "Despesas com Materiais";//Tipo de dedução, os tipos de dedução estão definidos no manual
                        PercentualDeduzir = 0; //Percentual a ser deduzido quando for dedução por percentual informar o valor percental
                        ValorDeduzir = 5.0;//Valor a ser deduzido quando for dedução por valor informar o valor.
                        //Os campos abaixo devem ser preenchidos quando a atividade permite dedução por material e houver dedução pela compra de materias.
                        CPFCNPJReferencia = ""; //CPF/CNPJ do fornecedor, quando houver dedução por material
                        NumeroNFReferencia = 0; //Número da NF de referência quando houver dedução por material
                        ValorTotalReferencia = 0; //Valor da NF de referência quando houver dedução por material.
                        Erro = dll.AdicionarDeducaoRPS(DeducaoPor, TipoDeducao, CPFCNPJReferencia, NumeroNFReferencia,
                                                      ValorTotalReferencia, PercentualDeduzir, ValorDeduzir);
                    if(Erro !=0)
                        JOptionPane.showMessageDialog(null, "Erro ao Adicionar Dedução");
            //Assincrono = "S";
            //Sucesso = "";
            //NumeroLote;
            //QtdNotasProcessadas;
            DocAssinatura="";
            Assincrono = "";
            Sucesso = "";
            NumeroLote = 0;
            QtdNotasProcessadas = 0;
            ValorTotalServicos = 0;
            ValorTotalDeducoes = 0;
            Erros = 0;
            Alertas = 0;

            //int nro=0;
            //IntegerByReference nro2 = new IntegerByReference(new Byte("1"));
            Integer f=1;
            f.byteValue();
//            byte[] nro2 = Integer.byteValue();
            
            //nro2.fromNative(Quantidade, n)
            //nro2.setValue("1");
            //System.out.println(nro2.getValue());
        //            IntegerByReference nro = new IntegerByReference("0");
            //System.out.println("Consultei:" + new String(nro2));
            
            //Erro=dll.ConsultarSequencial(6291, "20278049000118", "3015297", SeriePrestacao,f.intValue());
            //System.out.println(dll.ObterErroInterno(Erro));
            //System.out.println(new Integer(f.byteValue()));
            
            //System.out.println("Nro:" + new String(nro2));
            //Erro=dll.ConsultarLote(6291, "20278049000118", 0, "  ","  ",QtdNotasProcessadas,"  ",ValorTotalServicos,ValorTotalDeducoes,
            //        Alertas,Erros);
            //System.out.println("Ret Consulta:" + Erro);
            //StringBuilder str = new StringBuilder();
            //long Erro1;
            
            dll.setURL("http://issdigital.campinas.sp.gov.br/WsNFe2/LoteRps.jws", "http://issdigital.campinas.sp.gov.br/WsNFe2/LoteRps.jws?wsdl?method=ReqEnviarLote");
            //S para caso queire que assine, PORÉM da erro de viola
           //JOptionPane.showMessageDialog(null, dll.ObterXmlEnvio("".toCharArray(),"".toCharArray(), "".toCharArray()));
            System.out.println(dll.ObterXmlEnvio("S".toCharArray(),"".toCharArray(), "".toCharArray()));
            //VAZIO
            //System.out.println(dll.AssinarXml(dll.ObterXmlEnvio("".toCharArray(),"".toCharArray(), "".toCharArray())));
           
            //System.out.println("http://issdigital.campinas.sp.gov.br/WsNFe2/LoteRps.jws".charAt(0));
            //System.out.println("URL:"+ Erro);
            
            //Gera o XML NO Máximo, enviar pelo WebService
            //System.out.println("S".charAt(0));
            Sucesso="";
            System.out.println("=============ENVIANDOOOOOOOOOO===========");
//            Erro= dll.EnviarSincrono(DocAssinatura.toCharArray(), "".toCharArray(), NumeroLote, QtdNotasProcessadas, ValorTotalServicos, ValorTotalDeducoes, Erros, Alertas);
            try {
               Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(NFSE.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Erro= dll.TesteEnviar("", Assincrono, "", NumeroLote,
            //        QtdNotasProcessadas, ValorTotalServicos, ValorTotalDeducoes, Erros, Alertas);
            //long n1=0,n2=0,n3=0,n4=0;
            //int ptr = dll.Enviar("" , Assincrono, Sucesso, n1, n2, ValorTotalServicos, ValorTotalDeducoes, n3, n4);
            //dll.ConsultarSequencial(CodCidade, CPFCNPJRemetente, InscricaoMunicipalPrestador, SeriePrestacao, NroUltimoRps);
            //Erro= dll.TesteEnviar(DocAssinatura);
            System.out.println("TesteEnviar:"+ Erro);
            System.out.println("=============SUCESS: "+Erro);
//                    System.out.println(Erro);
      System.out.println("Dll SAT Iniciada com sucesso!");
//            INSTANCE.getClass().getClassLoader().loadClass("ServicosNFe");
//             dll;
      Random gera = new Random();
      long Session=Math.abs(gera.nextInt(10));
      //if(Session>999999)Session=Session/10;
      //if(Session<99999)Session=Session*10;
      
      String cCNPJ,cAtivacao;
      int cSessao,cUF;
System.out.println("Sessão :" + Session);
      
  cCNPJ = "11111111111111";
  cAtivacao = "LALALALAL";
  cUF = 35;
//  cSessao = 1101;
 // cSessao = Session;
  //cSessao=900000102;
     
     //dll.AtivarSAT(cSessao, 1, cAtivacao, cCNPJ, cUF);
     
     //System.out.println("Ativado com sucesso!");
 
     //System.out.println(dll.AtivarSAT());//.replace("iso-8859-1", "utf-8"));
     System.out.println("Processado com sucesso!");
     
     //dll.ComunicarCertificadoICPBRASIL(cSessao, cAtivacao, "das321dasdadadaus98dhas8mn"
      //       + "98dc3nfyhm8dy7fnmasdny7nasdasas322423das321dasdadadaus98dhas8mn98dc3n"
     //        + "fyhm8dy7fnmasdny7nasdasas322423das321dasdadadaus98dhas8mn98dc3nfyhm8"
     //        + "dy7fnmasdny7nasdasas322423das321dasdadadaus98dhas8mn98dc3nfyhm8dy7fnmas"
     //        + "dny7nasdasas322423das321dasdadadaus98dhas8mn98dc3nfyhm8dy7fnmasdny7nasdasas322423das321dasdadadaus98dhas8mn98");
    // System.out.println(dll.ComunicarCertificadoICPBRASIL());
     
            //System.out.println("Processor Type : "+systeminfo.dwProcessorType);
                  //INSTANCE.GetSystemTime(time);

              //    System.out.println("Day of the Week "+time.wDayOfWeek);
                //  System.out.println("Year :  "+time.wYear);
                   
                  //SYSTEM_INFO systeminfo=new SYSTEM_INFO();
                  //INSTANCE.GetSystemInfo(systeminfo);
                  //System.out.println("Processor Type : "+systeminfo.dwProcessorType);

    }

}
//Métodos da DLL, são assinados, de acordo com a documentação do SAT;
//Métodos da DLL, são assinados, de acordo com a documentação do SAT;
//Métodos da DLL, são assinados, de acordo com a documentação do SAT;
interface ServicosNFse extends Library{
    ServicosNFse INSTANCE = (ServicosNFse) Native.loadLibrary("lotenfse.dll", ServicosNFse.class);
    public void CriarLote(int a,String b, String c);
    public int AdicionarRPSV1(String InscricaoMunicipalPrestador,
                                               String RazaoSocialPrestador,
                                               String TipoRPS,
                                               String SerieRPS,
                                                  int NumeroRPS,
                                               String DataEmissaoRPS,
                                               String SituacaoRPS,
                                               String SerieRPSSubstituido,
                                                  int NumeroRPSSubstituido,
                                                  int NumeroNFSeSubstituida,
                                               String DataEmissaoNFSeSubstituida,
                                               String SeriePrestacao,
                                               String InscricaoMunicipalTomador,
                                               String CPFCNPJTomador,
                                               String RazaoSocialTomador,
                                               String DocTomadorEstrangeiro,
                                               String TipoLogradouroTomador,
                                               String LogradouroTomador,
                                               String NumeroEnderecoTomador,
                                               String ComplementoTomador,
                                               String TipoBairroTomador,
                                               String BairroTomador,
                                                  int CidadeTomador,
                                               String CidadeTomadorDescricao,
                                               String CEPTomador,
                                               String EmailTomador,
                                               String CodigoAtividade,
                                               double AliquotaAtividade,
                                               String TipoRecolhimento,
                                                  int MunicipioPrestacao,
                                               String MunicipioPrestacaoDescricao,
                                               String Operacao,
                                               String Tributacao,
                                               double ValorPIS,
                                               double ValorCOFINS,
                                               double ValorINSS,
                                               double ValorIR,
                                               double ValorCSLL,
                                               double AliquotaPIS,
                                               double AliquotaCOFINS,
                                               double AliquotaINSS,
                                               double AliquotaIR,
                                               double AliquotaCSLL,
                                               String DescricaoRPS,
                                               String DDDPrestador,
                                               String TelefonePrestador,
                                               String DDDTomador,
                                               String TelefoneTomador,
                                               String MotCancelamento,
                                               String CpfCnpjIntermediario);    
//    public int Enviar(String pDocAssinatura, String pAssincrono, String pSucesso, long pNumeroLote , long pQtdNotasProcessadas, double pValorTotalServicos , double pValorTotalDeducoes, long pErros,long pAlertas);
//    public int TesteEnviar(char[1] pDocAssinatura, char[1] pAssincrono, char[5] pSucesso, int pNumeroLote , int pQtdNotasProcessadas, double pValorTotalServicos , double pValorTotalDeducoes, int pErros,int pAlertas);
    //public int TesteEnviar(String pDocAssinatura);
    public int AdicionarDeducaoRPS(String DeducaoPor, String TipoDeducao,String CPFCNPJReferencia, int NumeroNFReferencia,
                                                      double ValorTotalReferencia, double PercentualDeduzir, double ValorDeduzir);
    public int AdicionarItemServicoRPS(String DiscriminacaoServico, double Quantidade,double ValorUnitario,String Tributavel);
    //public void setURL(String URL,String HTML);
    public Pointer setURL(String a, String ai);
    public int ConsultarSequencial(int CodCidade,String CPFCNPJRemetente,String InscricaoMunicipalPrestador,String SeriePrestacao,int NroUltimoRps);
    public int ConsultarLote(int CodCidade,String CPFCNPJRemetente,int NumeroLote,String Sucesso,String DataEnvioLote,int QtdNotasProcessadas,
            String TempoProcessamento,double ValorTotalServicos,double ValorTotalDeducoes,int Alertas,int Erros);
    //public String AssinarXml(String strXML);

    public String ObterXmlEnvio(char[]pDocAssinatura,char[] pMetodo , char[] pVersaoComponente);
    public String ObterErroInterno(int Codigo);
    //    public void funCancelaNFe(String xChaveNFe,String xProtocolo,String xJustificativa,String xUFNota,String xCertificado,String
//            xAmbienteNFe,String xVersaoXML,String xPathArquivos,String xPathXML,String xPathSchema,String xSchema,String xNFeCancelamento);
//    public void funConsultaDisponibilidade(String xPortal,String xUF,String xCertificado,String xVersao,String xAmbiente);
//    public void funConsultaNFe(String xChaveNFe,String xCertificado,String xUFNota,String xVersaoXML,String xAmbienteNFe,
//            String xPathSchema,String xSchema,String xNFeConsulta,String xPathRetorno);
//    public void funContTransmitir(String xTipo,String xNotaFiscal,String xChaveNFe,String xXMLAssinado,String xCertificado, 
//            String xUFAssinado,String xVersaoXML,String xAmbienteNFe,String xPathRetorno,String xPathLote,String xPathXML,
//            String xNFeRecepcao,String xNFeRetRecepcao);
//    public void funInutulizaNumeracao(String xNumero,String xSerie,String xAno,String xUFEmpresa,String xCNPJEmpresa
//            ,String xCertificado,String xMotivo,String xVersaoXML,String xAmbienteNFe,String xNFeInutlizacao
//            ,String xPathSchemas,String xSchema,String xPathXML,String xPathRetornos);
//    public void funLerXML(String strXML);
//    public void funTransmitir(String xTipo,String xChave,String xCertificado,String xArquivoXML,String xPathAssinado);
//    public void ServicosNFe();

    public int Enviar(String DocAssinatura, String Assincrono, String Sucessful, long NumeroLote, long QtdNotasProcessadas, double ValorTotalServicos, double ValorTotalDeducoes, long Erros, long Alertas);
    //public int Enviar(char DocAssinatura[], char Assincrono[], char string[], int NumeroLote, int QtdNotasProcessadas, double ValorTotalServicos, double ValorTotalDeducoes, int Erros, int Alertas);
    public int EnviarSincrono(char DocAssinatura[],char string[], int NumeroLote, int QtdNotasProcessadas, double ValorTotalServicos, double ValorTotalDeducoes, int Erros, int Alertas);

    //public int TesteEnviar(char[] toCharArray, char[] toCharArray0, char[] toCharArray1, int NumeroLote, int QtdNotasProcessadas, double ValorTotalServicos, double ValorTotalDeducoes, int Erros, int Alertas);

    public int TesteEnviar(String string, String Assincrono, String string0, int NumeroLote, int QtdNotasProcessadas, double ValorTotalServicos, double ValorTotalDeducoes, int Erros, int Alertas);
}
//Métodos da DLL, são assinados, de acordo com a documentação do SAT;
interface NFSEUtil extends Library{
    
}
interface IntCupomFiscal extends Library{
    //Carrega a Biblioteca SAT
    IntCupomFiscal INSTANCE = (IntCupomFiscal) Native.loadLibrary("C:\\SAT\\SAT.dll", IntCupomFiscal.class);
    public void AtivarSAT(int numeroSessao,int subComando,String codigoDeAtivacao,String CNPJ,int cUF);
    public String AtivarSAT();
    public void ComunicarCertificadoICPBRASIL(int numeroSessao,String codigoDeAtivacao,String certificado);
    public String ComunicarCertificadoICPBRASIL();
    public void EnviarDadosVenda(int numeroSessao,String codigoDeAtivacao,String dadosVenda);
    public String EnviarDadosVenda();
    public void CancelarUltimaVenda(int numeroSessao,String codigoDeAtivacao,String chave,String dadosCancelamento);
    public String CancelarUltimaVenda();
    public void ConsultarSAT(int numeroSessao);
    public String ConsultarSAT();
    public void TesteFimAFim(int numeroSessao,String codigoDeAtivacao,String dadosVenda);
    public String TesteFimAFim();
    public void ConsultarStatusOperacional(int numeroSessao,String codigoDeAtivacao);
    public String ConsultarStatusOperacional();
    public void ConsultarNumeroSessao(int numeroSessao,String codigoDeAtivacao,int cNumeroDeSessao);
    public String ConsultarNumeroSessao();
    public void ConfigurarInterfaceDeRede(int numeroSessao,String codigoDeAtivacao,String dadosConfiguracao);
    public String ConfigurarInterfaceDeRede();
    public void AssociarAssinatura(int numeroSessao,String codigoDeAtivacao,String CNPJvalue,String assinaturaCNPJs);
    public String AssociarAssinatura();
    public void AtualizarSoftwareSAT(int numeroSessao,String codigoDeAtivacao);
    public String AtualizarSoftwareSAT();
    public void ExtrairLogs(int numeroSessao,String codigoDeAtivacao);
    public String ExtrairLogs();    
    public void BloquearSAT(int numeroSessao,String codigoDeAtivacao);
    public String BloquearSAT();
    public void DesbloquearSAT(int numeroSessao,String codigoDeAtivacao);
    public String DesbloquearSAT();
    public void TrocarCodigoDeAtivacao(int numeroSessao,String codigoDeAtivacao,int opcao,String novoCodigo,String confNovoCodigo);
    public String TrocarCodigoDeAtivacao();
    
//    IntCupomFiscal INSTANCE = (IntCupomFiscal) Native.loadLibrary("C:\\SAT\\SAT.dll", IntCupomFiscal.class);
}