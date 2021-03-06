/* Generated By:JavaCC: Do not edit this line. Parser.java */
package edu.itsco;

import java.util.ArrayList;

public class Parser implements ParserConstants {

  private static ArrayList<Variable> listaVariable;
  public static void main(String args [])
  {
    listaVariable = new ArrayList<Variable>();
    Parser parser = new Parser(System.in);

   try {
    System.out.println("Biembenidos a mi compilador Miri");
    Parser.gramaticaPrincipal();
     System.out.println("Compilacion exitosa att:Miri");
   }catch (ParseException pex) {
                System.err.println(pex.getMessage());
      }catch (SemanticException es) {
        System.err.println(es.getMessage());

      }
  }

  private static boolean existeVariable(Variable variable) {
        return listaVariable.contains(variable);
  }

  private static void agregarVariable(Variable variable)
  throws SemanticException{
    if(!existeVariable(variable)){
      listaVariable.add(variable);
       }else {
                throw new SemanticException(variable, SemanticException.Tipo.VARIABLE_DUPLICADA);
       }
    }
   private static void variableDefinida(Variable variable)
    throws SemanticException{
        if(!existeVariable(variable)){
      throw new SemanticException (variable,
      SemanticException.Tipo.VARIABLE_NO_DEFINIDA);
       }
     }

//SECCION DE GRAMATICA
  static final public void gramaticaPrincipal() throws ParseException, SemanticException {
    jj_consume_token(PROGRAMA);
    jj_consume_token(ID);
    jj_consume_token(ALL);
    gramaticaSentencias();
    jj_consume_token(CLL);
  }

  static final public void gramaticaSentencias() throws ParseException, SemanticException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENTERO:
      case CADENA:
      case DECIMAL:
        gramaticaDeclararVariables();
        break;
      case CAPTURAR:
        gramaticaCapturar();
        break;
      case IMPRIMIR:
        gramaticaImprimir();
        break;
      case SI:
        gramaticaSi();
        break;
      case MIENTRAS:
        gramaticaMientras();
        break;
      case HACER:
        gramaticaHacerMientras();
        break;
      case SELECCIONA:
        gramaticaSelecciona();
        break;
      case ID:
        gramaticaAsignacion();
        break;
      case PARA:
        gramaticaPara();
        break;
      default:
        jj_la1[0] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENTERO:
      case CADENA:
      case DECIMAL:
      case IMPRIMIR:
      case CAPTURAR:
      case SI:
      case SELECCIONA:
      case PARA:
      case MIENTRAS:
      case HACER:
      case ID:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
    }
  }

  static final public void gramaticaDeclararVariables() throws ParseException, SemanticException {
  Token id;
  Token tipoDato;
    tipoDato = tipoDato();
    id = jj_consume_token(ID);
          Variable v = new Variable(id.image,tipoDato.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IGUAL:
      jj_consume_token(IGUAL);
      valor();
         v.setEstaInicializada(true);
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
      agregarVariable(v);
    jj_consume_token(PC);
  }

  static final public Token tipoDato() throws ParseException {
  Token tipoDato;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTERO:
      tipoDato = jj_consume_token(ENTERO);
      break;
    case CADENA:
      tipoDato = jj_consume_token(CADENA);
      break;
    case DECIMAL:
      tipoDato = jj_consume_token(DECIMAL);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
         {if (true) return tipoDato;}
    throw new Error("Missing return statement in function");
  }

  static final public void valor() throws ParseException, SemanticException {
  Token id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VALOR_ENTERO:
      jj_consume_token(VALOR_ENTERO);
      break;
    case VALOR_CADENA:
      jj_consume_token(VALOR_CADENA);
      break;
    case VALOR_DECIMAL:
      jj_consume_token(VALOR_DECIMAL);
      break;
    case ID:
      id = jj_consume_token(ID);
        Variable v = new Variable();
        v.setId(id.image);
        variableDefinida(v);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void gramaticaCapturar() throws ParseException, SemanticException {
  Token id;
    jj_consume_token(CAPTURAR);
    jj_consume_token(AP);
    id = jj_consume_token(ID);
    jj_consume_token(CP);
    jj_consume_token(PC);
        Variable v = new Variable();
        v.setId(id.image);
        variableDefinida(v);
  }

  static final public void gramaticaImprimir() throws ParseException, SemanticException {
    jj_consume_token(IMPRIMIR);
    jj_consume_token(AP);
    valor();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUMA:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_2;
      }
      jj_consume_token(SUMA);
      valor();
    }
    jj_consume_token(CP);
    jj_consume_token(PC);
  }

  static final public void gramaticaSi() throws ParseException, SemanticException {
    jj_consume_token(SI);
    jj_consume_token(AP);
    condicion();
    jj_consume_token(CP);
    jj_consume_token(ALL);
    gramaticaSentencias();
    jj_consume_token(CLL);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SINO:
      jj_consume_token(SINO);
      jj_consume_token(ALL);
      gramaticaSentencias();
      jj_consume_token(CLL);
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
  }

  static final public void condicion() throws ParseException, SemanticException {
    evaluacionSimple();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
      case OR:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_3;
      }
      opLogico();
      evaluacionSimple();
    }
  }

  static final public void evaluacionSimple() throws ParseException, SemanticException {
    valor();
    opRelacional();
    valor();
  }

  static final public void opRelacional() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MAYOR:
      jj_consume_token(MAYOR);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IGUAL:
        jj_consume_token(IGUAL);
        break;
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      break;
    case MENOR:
      jj_consume_token(MENOR);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IGUAL:
        jj_consume_token(IGUAL);
        break;
      default:
        jj_la1[9] = jj_gen;
        ;
      }
      break;
    case NOT:
      jj_consume_token(NOT);
      jj_consume_token(IGUAL);
      break;
    case IGUAL:
      jj_consume_token(IGUAL);
      jj_consume_token(IGUAL);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opLogico() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AND:
      jj_consume_token(AND);
      break;
    case OR:
      jj_consume_token(OR);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void gramaticaMientras() throws ParseException, SemanticException {
    jj_consume_token(MIENTRAS);
    jj_consume_token(AP);
    condicion();
    jj_consume_token(CP);
    jj_consume_token(ALL);
    gramaticaSentencias();
    jj_consume_token(CLL);
  }

  static final public void gramaticaHacerMientras() throws ParseException, SemanticException {
    jj_consume_token(HACER);
    jj_consume_token(ALL);
    gramaticaSentencias();
    jj_consume_token(CLL);
    jj_consume_token(MIENTRAS);
    jj_consume_token(AP);
    condicion();
    jj_consume_token(CP);
    jj_consume_token(PC);
  }

  static final public void gramaticaSelecciona() throws ParseException, SemanticException {
    jj_consume_token(SELECCIONA);
    jj_consume_token(AP);
    jj_consume_token(ID);
    jj_consume_token(CP);
    jj_consume_token(ALL);
    label_4:
    while (true) {
      jj_consume_token(CASO);
      jj_consume_token(VALOR_ENTERO);
      jj_consume_token(PP);
      gramaticaSentencias();
      jj_consume_token(ROMPER);
      jj_consume_token(PC);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CASO:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_4;
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DEFECTO:
      jj_consume_token(DEFECTO);
      gramaticaSentencias();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ROMPER:
        jj_consume_token(ROMPER);
        jj_consume_token(PC);
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
    jj_consume_token(CLL);
  }

  static final public void gramaticaAsignacion() throws ParseException, SemanticException {
        Token id;
    id = jj_consume_token(ID);
    jj_consume_token(IGUAL);
    operacionSimple();
    jj_consume_token(PC);
       Variable v = new Variable();
        v.setId(id.image);
        variableDefinida(v);
  }

  static final public void operacionSimple() throws ParseException, SemanticException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
    case VALOR_ENTERO:
    case VALOR_DECIMAL:
    case VALOR_CADENA:
      valor();
      break;
    case AP:
      operacionParentesis();
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUMA:
      case RESTA:
      case MULTIPLICACION:
      case DIVICION:
      case RESIDUO:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_5;
      }
      operadorAritmetico();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
      case VALOR_ENTERO:
      case VALOR_DECIMAL:
      case VALOR_CADENA:
        valor();
        break;
      case AP:
        operacionParentesis();
        break;
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void operacionParentesis() throws ParseException, SemanticException {
    jj_consume_token(AP);
    operacionSimple();
    jj_consume_token(CP);
  }

  static final public void operadorAritmetico() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUMA:
      jj_consume_token(SUMA);
      break;
    case RESTA:
      jj_consume_token(RESTA);
      break;
    case DIVICION:
      jj_consume_token(DIVICION);
      break;
    case MULTIPLICACION:
      jj_consume_token(MULTIPLICACION);
      break;
    case RESIDUO:
      jj_consume_token(RESIDUO);
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void gramaticaPara() throws ParseException, SemanticException {
    jj_consume_token(PARA);
    jj_consume_token(AP);
    jj_consume_token(ID);
    jj_consume_token(IGUAL);
    valor();
    jj_consume_token(PC);
    condicion();
    jj_consume_token(PC);
    jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUMA:
      jj_consume_token(SUMA);
      jj_consume_token(SUMA);
      break;
    case RESTA:
      jj_consume_token(RESTA);
      jj_consume_token(RESTA);
      break;
    case IGUAL:
      jj_consume_token(IGUAL);
      operacionSimple();
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(CP);
    jj_consume_token(ALL);
    gramaticaSentencias();
    jj_consume_token(CLL);
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[20];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1c5f80,0x1c5f80,0x8000000,0x380,0x0,0x200000,0x2000,0xc0000000,0x8000000,0x8000000,0x38000000,0xc0000000,0x8000,0x10000,0x20000,0x0,0x3e00000,0x0,0x3e00000,0x8600000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x200,0x200,0x0,0x0,0x1e00,0x0,0x0,0x0,0x0,0x0,0x1,0x0,0x0,0x0,0x0,0x1e02,0x0,0x1e02,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 20; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[45];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 20; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 45; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
