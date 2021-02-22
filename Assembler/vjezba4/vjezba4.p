                       CTCR `EQU FFFF0000 
                       CTLR `EQU FFFF0004 
                       CTSTAT `EQU FFFF0008 
                       CTGOTOV `EQU FFFF000C 
                        
                       DMASRC `EQU FFFF1000 
                       DMADEST `EQU FFFF1004 
                       DMABROJAC `EQU FFFF1008 
                       DMACR `EQU FFFF100C 
                       DMASTART `EQU FFFF1010 
                       DMAPRIHVAT `EQU FFFF1014 
                        
                       BVJ `EQU FFFFFFFC 
                        
                        
                       `ORG 0 
00000000  00 00 81 07  		MOVE 10000, SP 
00000004  58 00 00 C4  		JP GLAVNI 
                       		 
                       `ORG 0C 
0000000C  00 00 00 88  		PUSH R0		;KONTEKST 
00000010  00 00 80 88  		PUSH R1 
00000014  00 00 20 00  		MOVE SR, R0 
00000018  00 00 00 88  		PUSH R0 
                       		 
0000001C  14 10 0F B8  		STORE R0, (DMAPRIHVATI) 
                       		 
00000020  C4 00 00 B0  		LOAD R0, (ODREDISTE)	;STAVLJANJE PODATKA IZ BVJ NA ODRE
00000024  24 00 00 24  		ADD R0, %D 36, R0		;STAVLJANJE -1 NA TO MJESTO PA POMIC
00000028  FF FF 8F 04  		MOVE -1, R1 
0000002C  00 00 80 BC  		STORE R1, (R0) 
00000030  04 00 00 24  		ADD R0, 4, R0 
00000034  C4 00 00 B8  		STORE R0, (ODREDISTE) 
                       		 
00000038  BC 00 00 B0  		LOAD R0, (BLOKOVI) 
0000003C  01 00 00 24  		ADD R0, 1, R0		;BROJAC BLOKOVA 
00000040  BC 00 00 B8  		STORE R0, (BLOKOVI) 
                       		 
00000044  00 00 00 80  		POP R0 
00000048  00 00 10 00  		MOVE R0, SR 
0000004C  00 00 80 80  		POP R1 
00000050  00 00 00 80  		POP R0 
                       		 
00000054  03 00 00 D8  		RETN		 
                       		 
                       		 
00000058  E8 03 00 04  GLAVNI	MOVE %D 1000, R0 
0000005C  04 00 0F B8  		STORE R0, (CTLR) 
00000060  01 00 00 04  		MOVE %B 001, R0 
00000064  00 00 0F B8  		STORE R0, (CTCR) 
                       		 
00000068  BC 00 00 B0  PETLJA	LOAD R0, (BLOKOVI) 
0000006C  05 00 00 6C  		CMP R0, 5 
00000070  3C 00 C0 D5  		JR_EQ KRAJ 
                        
00000074  08 00 0F B0  		LOAD R0, (CTSTAT) 
00000078  00 00 00 08  		OR R0, R0, R0 
0000007C  E8 FF CF D5  		JR_Z PETLJA 
                       		 
00000080  08 00 0F B8  		STORE R0, (CTSTAT) 
00000084  0C 00 0F B8  		STORE R0, (CTGOTOV) 
                       		 
                       		 
00000088  FC FF 0F 04  		MOVE 0FFFFFFFC, R0 
0000008C  00 10 0F B8  		STORE R0, (DMASRC) 
00000090  C4 00 00 B0  		LOAD R0, (ODREDISTE) 
00000094  04 10 0F B8  		STORE R0, (DMADEST) 
00000098  09 00 00 04  		MOVE 9, R0 
0000009C  08 10 0F B8  		STORE R0, (DMABROJAC) 
000000A0  07 00 00 04  		MOVE %B 0111, R0 
000000A4  0C 10 0F B8  		STORE R0, (DMACR) 
000000A8  10 10 0F B8  		STORE R0, (DMASTART) 
000000AC  B8 FF 0F D4  		JR PETLJA 
                       		 
                       				 
000000B0  00 00 00 04  KRAJ	MOVE 0, R0 
000000B4  00 00 0F B8  		STORE R0, (CTCR) 
000000B8  00 00 00 F8  		HALT 
                        
                        
                        
000000BC  00 00 00 00  BLOKOVI `DW 00,00,00,00 
000000C0  00 09 00 00  POM `DW 00,09,00,00 
000000C4  00 10 00 00  ODREDISTE `DW 00,10,00,00 
                        
