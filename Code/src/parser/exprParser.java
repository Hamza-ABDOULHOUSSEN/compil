// Generated from expr.g4 by ANTLR 4.9.2

package parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class exprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		OPERATEUR=18, INT=19, IDF=20, WS=21;
	public static final int
		RULE_fichier = 0, RULE_decl = 1, RULE_decl_vars = 2, RULE_decl_typ = 3, 
		RULE_decl_fct = 4, RULE_param = 5, RULE_expr = 6, RULE_instruction = 7, 
		RULE_bloc = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"fichier", "decl", "decl_vars", "decl_typ", "decl_fct", "param", "expr", 
			"instruction", "bloc"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "','", "';'", "'struct'", "'*'", "'{'", "'}'", "'('", 
			"')'", "'->'", "'!'", "'-'", "'sizeof'", "'if'", "'else'", "'while'", 
			"'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "OPERATEUR", "INT", "IDF", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "expr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public exprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FichierContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(exprParser.EOF, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public FichierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fichier; }
	}

	public final FichierContext fichier() throws RecognitionException {
		FichierContext _localctx = new FichierContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_fichier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__3) {
				{
				{
				setState(18);
				decl();
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(24);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public Decl_typContext decl_typ() {
			return getRuleContext(Decl_typContext.class,0);
		}
		public Decl_fctContext decl_fct() {
			return getRuleContext(Decl_fctContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			setState(28);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				decl_typ();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				decl_fct();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decl_varsContext extends ParserRuleContext {
		public List<TerminalNode> IDF() { return getTokens(exprParser.IDF); }
		public TerminalNode IDF(int i) {
			return getToken(exprParser.IDF, i);
		}
		public Decl_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_vars; }
	}

	public final Decl_varsContext decl_vars() throws RecognitionException {
		Decl_varsContext _localctx = new Decl_varsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl_vars);
		int _la;
		try {
			setState(48);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				match(T__0);
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(31);
					match(IDF);
					}
					}
					setState(34); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDF );
				setState(36);
				match(T__1);
				setState(37);
				match(T__2);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
				match(T__3);
				setState(39);
				match(IDF);
				setState(42); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(40);
					match(T__4);
					setState(41);
					match(IDF);
					}
					}
					setState(44); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__4 );
				setState(46);
				match(T__1);
				setState(47);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decl_typContext extends ParserRuleContext {
		public TerminalNode IDF() { return getToken(exprParser.IDF, 0); }
		public List<Decl_varsContext> decl_vars() {
			return getRuleContexts(Decl_varsContext.class);
		}
		public Decl_varsContext decl_vars(int i) {
			return getRuleContext(Decl_varsContext.class,i);
		}
		public Decl_typContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_typ; }
	}

	public final Decl_typContext decl_typ() throws RecognitionException {
		Decl_typContext _localctx = new Decl_typContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl_typ);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(T__3);
			setState(51);
			match(IDF);
			setState(52);
			match(T__5);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__3) {
				{
				{
				setState(53);
				decl_vars();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			match(T__6);
			setState(60);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decl_fctContext extends ParserRuleContext {
		public TerminalNode IDF() { return getToken(exprParser.IDF, 0); }
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public Decl_fctContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_fct; }
	}

	public final Decl_fctContext decl_fct() throws RecognitionException {
		Decl_fctContext _localctx = new Decl_fctContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_decl_fct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(T__0);
			setState(63);
			match(IDF);
			setState(64);
			match(T__7);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__3) {
				{
				{
				setState(65);
				param();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
			match(T__1);
			setState(72);
			match(T__8);
			setState(73);
			bloc();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public List<TerminalNode> IDF() { return getTokens(exprParser.IDF); }
		public TerminalNode IDF(int i) {
			return getToken(exprParser.IDF, i);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_param);
		try {
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				match(T__0);
				setState(76);
				match(IDF);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				match(T__3);
				setState(78);
				match(IDF);
				setState(79);
				match(T__4);
				setState(80);
				match(IDF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(exprParser.INT, 0); }
		public TerminalNode IDF() { return getToken(exprParser.IDF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPERATEUR() { return getToken(exprParser.OPERATEUR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(84);
				match(INT);
				}
				break;
			case 2:
				{
				setState(85);
				match(IDF);
				}
				break;
			case 3:
				{
				setState(86);
				match(IDF);
				setState(87);
				match(T__7);
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << INT) | (1L << IDF))) != 0)) {
					{
					{
					setState(88);
					expr(0);
					}
					}
					setState(93);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(94);
				match(T__1);
				setState(95);
				match(T__8);
				}
				break;
			case 4:
				{
				setState(96);
				match(T__10);
				setState(97);
				expr(5);
				}
				break;
			case 5:
				{
				setState(98);
				match(T__11);
				setState(99);
				expr(4);
				}
				break;
			case 6:
				{
				setState(100);
				match(T__12);
				setState(101);
				match(T__7);
				setState(102);
				match(T__3);
				setState(103);
				match(IDF);
				setState(104);
				match(T__8);
				}
				break;
			case 7:
				{
				setState(105);
				match(T__7);
				setState(106);
				expr(0);
				setState(107);
				match(T__8);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(117);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(111);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(112);
						match(OPERATEUR);
						setState(113);
						expr(4);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(114);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(115);
						match(T__9);
						setState(116);
						match(IDF);
						}
						break;
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_instruction);
		try {
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				expr(0);
				setState(124);
				match(T__2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(126);
				match(T__13);
				setState(127);
				match(T__7);
				setState(128);
				expr(0);
				setState(129);
				match(T__8);
				setState(130);
				instruction();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				match(T__13);
				setState(133);
				match(T__7);
				setState(134);
				expr(0);
				setState(135);
				match(T__8);
				setState(136);
				instruction();
				setState(137);
				match(T__14);
				setState(138);
				instruction();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(140);
				match(T__15);
				setState(141);
				match(T__7);
				setState(142);
				expr(0);
				setState(143);
				match(T__8);
				setState(144);
				instruction();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(146);
				bloc();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(147);
				match(T__16);
				setState(148);
				expr(0);
				setState(149);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocContext extends ParserRuleContext {
		public List<Decl_varsContext> decl_vars() {
			return getRuleContexts(Decl_varsContext.class);
		}
		public Decl_varsContext decl_vars(int i) {
			return getRuleContext(Decl_varsContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public BlocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloc; }
	}

	public final BlocContext bloc() throws RecognitionException {
		BlocContext _localctx = new BlocContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_bloc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__5);
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__3) {
				{
				{
				setState(154);
				decl_vars();
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << INT) | (1L << IDF))) != 0)) {
				{
				{
				setState(160);
				instruction();
				}
				}
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(166);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\u00ab\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\7"+
		"\2\26\n\2\f\2\16\2\31\13\2\3\2\3\2\3\3\3\3\5\3\37\n\3\3\4\3\4\6\4#\n\4"+
		"\r\4\16\4$\3\4\3\4\3\4\3\4\3\4\3\4\6\4-\n\4\r\4\16\4.\3\4\3\4\5\4\63\n"+
		"\4\3\5\3\5\3\5\3\5\7\59\n\5\f\5\16\5<\13\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\7\6E\n\6\f\6\16\6H\13\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7T"+
		"\n\7\3\b\3\b\3\b\3\b\3\b\3\b\7\b\\\n\b\f\b\16\b_\13\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bp\n\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\7\bx\n\b\f\b\16\b{\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\5\t\u009a\n\t\3\n\3\n\7\n\u009e\n\n\f\n\16\n\u00a1\13\n\3\n\7"+
		"\n\u00a4\n\n\f\n\16\n\u00a7\13\n\3\n\3\n\3\n\2\3\16\13\2\4\6\b\n\f\16"+
		"\20\22\2\2\2\u00ba\2\27\3\2\2\2\4\36\3\2\2\2\6\62\3\2\2\2\b\64\3\2\2\2"+
		"\n@\3\2\2\2\fS\3\2\2\2\16o\3\2\2\2\20\u0099\3\2\2\2\22\u009b\3\2\2\2\24"+
		"\26\5\4\3\2\25\24\3\2\2\2\26\31\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30"+
		"\32\3\2\2\2\31\27\3\2\2\2\32\33\7\2\2\3\33\3\3\2\2\2\34\37\5\b\5\2\35"+
		"\37\5\n\6\2\36\34\3\2\2\2\36\35\3\2\2\2\37\5\3\2\2\2 \"\7\3\2\2!#\7\26"+
		"\2\2\"!\3\2\2\2#$\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%&\3\2\2\2&\'\7\4\2\2\'"+
		"\63\7\5\2\2()\7\6\2\2),\7\26\2\2*+\7\7\2\2+-\7\26\2\2,*\3\2\2\2-.\3\2"+
		"\2\2.,\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\4\2\2\61\63\7\5\2\2\62 \3"+
		"\2\2\2\62(\3\2\2\2\63\7\3\2\2\2\64\65\7\6\2\2\65\66\7\26\2\2\66:\7\b\2"+
		"\2\679\5\6\4\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2<:\3"+
		"\2\2\2=>\7\t\2\2>?\7\5\2\2?\t\3\2\2\2@A\7\3\2\2AB\7\26\2\2BF\7\n\2\2C"+
		"E\5\f\7\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2"+
		"IJ\7\4\2\2JK\7\13\2\2KL\5\22\n\2L\13\3\2\2\2MN\7\3\2\2NT\7\26\2\2OP\7"+
		"\6\2\2PQ\7\26\2\2QR\7\7\2\2RT\7\26\2\2SM\3\2\2\2SO\3\2\2\2T\r\3\2\2\2"+
		"UV\b\b\1\2Vp\7\25\2\2Wp\7\26\2\2XY\7\26\2\2Y]\7\n\2\2Z\\\5\16\b\2[Z\3"+
		"\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^`\3\2\2\2_]\3\2\2\2`a\7\4\2\2ap"+
		"\7\13\2\2bc\7\r\2\2cp\5\16\b\7de\7\16\2\2ep\5\16\b\6fg\7\17\2\2gh\7\n"+
		"\2\2hi\7\6\2\2ij\7\26\2\2jp\7\13\2\2kl\7\n\2\2lm\5\16\b\2mn\7\13\2\2n"+
		"p\3\2\2\2oU\3\2\2\2oW\3\2\2\2oX\3\2\2\2ob\3\2\2\2od\3\2\2\2of\3\2\2\2"+
		"ok\3\2\2\2py\3\2\2\2qr\f\5\2\2rs\7\24\2\2sx\5\16\b\6tu\f\t\2\2uv\7\f\2"+
		"\2vx\7\26\2\2wq\3\2\2\2wt\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\17\3"+
		"\2\2\2{y\3\2\2\2|\u009a\7\5\2\2}~\5\16\b\2~\177\7\5\2\2\177\u009a\3\2"+
		"\2\2\u0080\u0081\7\20\2\2\u0081\u0082\7\n\2\2\u0082\u0083\5\16\b\2\u0083"+
		"\u0084\7\13\2\2\u0084\u0085\5\20\t\2\u0085\u009a\3\2\2\2\u0086\u0087\7"+
		"\20\2\2\u0087\u0088\7\n\2\2\u0088\u0089\5\16\b\2\u0089\u008a\7\13\2\2"+
		"\u008a\u008b\5\20\t\2\u008b\u008c\7\21\2\2\u008c\u008d\5\20\t\2\u008d"+
		"\u009a\3\2\2\2\u008e\u008f\7\22\2\2\u008f\u0090\7\n\2\2\u0090\u0091\5"+
		"\16\b\2\u0091\u0092\7\13\2\2\u0092\u0093\5\20\t\2\u0093\u009a\3\2\2\2"+
		"\u0094\u009a\5\22\n\2\u0095\u0096\7\23\2\2\u0096\u0097\5\16\b\2\u0097"+
		"\u0098\7\5\2\2\u0098\u009a\3\2\2\2\u0099|\3\2\2\2\u0099}\3\2\2\2\u0099"+
		"\u0080\3\2\2\2\u0099\u0086\3\2\2\2\u0099\u008e\3\2\2\2\u0099\u0094\3\2"+
		"\2\2\u0099\u0095\3\2\2\2\u009a\21\3\2\2\2\u009b\u009f\7\b\2\2\u009c\u009e"+
		"\5\6\4\2\u009d\u009c\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\u00a5\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a4\5\20"+
		"\t\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\7\t"+
		"\2\2\u00a9\23\3\2\2\2\21\27\36$.\62:FS]owy\u0099\u009f\u00a5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}