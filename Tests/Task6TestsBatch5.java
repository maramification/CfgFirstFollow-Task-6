package csen1002.tests.task6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task6.CfgFirstFollow;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task6TestsBatch5 {

	@Test
	public void testCfg1First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;H;Q;O;V;C#d;f;h;l;o;t;x#S/tOQl,lOV,OtSt,lCx,O;H/xQVVC,V,oQS,Q;Q/C,HoOo;O/SlSh,dV,e,S;V/oVf,Vt,e,V;C/QC,dSd,OS,VVoCS");
		assertEquals("S/delt;H/delotx;Q/delotx;O/delt;V/eot;C/delotx", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg1Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;H;Q;O;V;C#d;f;h;l;o;t;x#S/tOQl,lOV,OtSt,lCx,O;H/xQVVC,V,oQS,Q;Q/C,HoOo;O/SlSh,dV,e,S;V/oVf,Vt,e,V;C/QC,dSd,OS,VVoCS");
		assertEquals("S/$dhlotx;H/o;Q/dlotx;O/$dhlotx;V/$dfhlotx;C/dlotx", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg2First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;O;X;P;G;H;T#a;b;d;h;p;u;z#S/zPTX,SSPd;O/GO,SaH,X,H;X/zXOuH,pSHb,H,X;P/XGhGz,dO,PbOp,e;G/SS,TPTu,uTGGX,p,bPu,SHSP,e,X;H/XTH,HuSS,e;T/Td,uS");
		assertEquals("S/z;O/bepuz;X/epuz;P/bdehpuz;G/bepuz;H/epuz;T/u", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg2Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;O;X;P;G;H;T#a;b;d;h;p;u;z#S/zPTX,SSPd;O/GO,SaH,X,H;X/zXOuH,pSHb,H,X;P/XGhGz,dO,PbOp,e;G/SS,TPTu,uTGGX,p,bPu,SHSP,e,X;H/XTH,HuSS,e;T/Td,uS");
		assertEquals("S/$abdhpuz;O/bdhpuz;X/$abdhpuz;P/bdhpuz;G/bdhpuz;H/$abdhpuz;T/$abdhpuz", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg3First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;D;W;A;H;K;T#a;i;u;y#S/TaHT,DWS,aW;D/aWW,yKDT,A,yAWiW,AT,W,S;W/i,a;A/TAS,iSyW,aAy,TDy,T,H,A,D;H/y,TWK,e,S;K/TS,yTW,uAH,e;T/aDW,y,Wy,ST,a,DSSSu");
		assertEquals("S/aiy;D/aeiy;W/ai;A/aeiy;H/aeiy;K/aeiuy;T/aiy", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg3Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;D;W;A;H;K;T#a;i;u;y#S/TaHT,DWS,aW;D/aWW,yKDT,A,yAWiW,AT,W,S;W/i,a;A/TAS,iSyW,aAy,TDy,T,H,A,D;H/y,TWK,e,S;K/TS,yTW,uAH,e;T/aDW,y,Wy,ST,a,DSSSu");
		assertEquals("S/$aiuy;D/aiy;W/$aiuy;A/aiy;H/aiy;K/aiy;T/$aiuy", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg4First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;L;M;T;Z;I;N#a;b;f;h;n;q#S/qThS,aSa,I;L/q,Z,e;M/IMhM,fLZhZ,e,I,M;T/nZ,fZMLn,Na,n,TTnSa,e;Z/ZSSbI,aIhL,T,Z;I/b,hMh;N/nTn,Z,IfZN,Nb,IhZ");
		assertEquals("S/abhq;L/abefhnq;M/befh;T/abefhnq;Z/abefhnq;I/bh;N/abefhnq", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg4Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;L;M;T;Z;I;N#a;b;f;h;n;q#S/qThS,aSa,I;L/q,Z,e;M/IMhM,fLZhZ,e,I,M;T/nZ,fZMLn,Na,n,TTnSa,e;Z/ZSSbI,aIhL,T,Z;I/b,hMh;N/nTn,Z,IfZN,Nb,IhZ");
		assertEquals("S/$abhq;L/abfhnq;M/abfhnq;T/abfhnq;Z/abfhnq;I/$abfhnq;N/ab", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg5First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;X;E;M;Z;A#a;f;l;n;p;v;w#S/ZwZ,fAEl;X/n,XpS,Xn,E,Z;E/fX,pAXA,M,E;M/l,fM,SaXa,vXw,ESf,nZ,e;Z/AAf,lAZXl,e;A/AwXAZ,pS");
		assertEquals("S/flpw;X/eflnpvw;E/eflnpvw;M/eflnpvw;Z/elp;A/p", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg5Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;X;E;M;Z;A#a;f;l;n;p;v;w#S/ZwZ,fAEl;X/n,XpS,Xn,E,Z;E/fX,pAXA,M,E;M/l,fM,SaXa,vXw,ESf,nZ,e;Z/AAf,lAZXl,e;A/AwXAZ,pS");
		assertEquals("S/$aflnpvw;X/aflnpw;E/aflnpw;M/aflnpw;Z/$aflnpvw;A/aflnpvw", cfgFirstFollow.follow());
	}

}