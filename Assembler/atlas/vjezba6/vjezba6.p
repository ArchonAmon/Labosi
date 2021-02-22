                       `ORG 0 
00000000  01 D8 A0 E3  		MOV SP, #1<16 
                       		 
00000004  00 00 0F E1  		MRS R0, CPSR 
00000008  80 00 C0 E3  		BIC R0, R0, #80			;OMOGUCAVANJE PREKIDA 
0000000C  00 F0 21 E1  		MSR CPSR_C, R0 
                        
00000010  03 00 00 EA  		B GLAVNI 
                       		 
                       `ORG 18 
00000018  08 00 00 EA  		B PREKID 
                       		 
                       		 
0000001C  C0 30 9F E5  GLAVNI 	LDR R3, GPIOADR		;ADRESA GPIO 
00000020  C4 40 9F E5  		LDR R4, RTC			;ADRESA RTC 
                       		 
00000024  BC 20 9F E5  		LDR R2, TIME		;INICIJALIZACIJA RTC JEDINICE 
00000028  04 20 84 E5  		STR R2, [R4, #4] 
0000002C  01 20 A0 E3  		MOV R2, #1			;OMOGUCAVAJE PREKIDA RTC JEDINICE 
00000030  10 20 84 E5  		STR R2, [R4, #10]	 
                       						 
00000034  00 00 00 EA  PETLJA	B PETLJA			;CEKANJE 
                       	 
                       		 
                       		 
                       		 
00000038  63 40 2D E9  PREKID	STMFD R13!, {R0, R1, R5, R6, R14} 
                        
0000003C  08 40 84 E5  		STR R4, [R4, #8]	;PRIHVACANJE PREKIDA 
00000040  00 10 A0 E3  		MOV R1, #0 
00000044  0C 10 84 E5  		STR R1, [R4, #0C]	;RESETIRANJE BROJILA 
                       		 
00000048  0D 00 A0 E3  		MOV R0, #0D			;BRISANJE TRENUTNOG EKRANA 
0000004C  1A 00 00 EB  		BL LCDWR 
                       		 
00000050  3E 00 A0 E3  		MOV R0, #3E			;ISPISIVANJE < 3 PUTA 
00000054  03 50 A0 E3  		MOV R5, #3 
                       		 
00000058  17 00 00 EB  ISPIS1	BL LCDWR 
0000005C  01 50 55 E2  		SUBS R5, R5, #1 
00000060  FE FF FF 1A  		BNE	ISPIS1 
                       		 
                       		 
00000064  84 10 9F E5  		LDR R1, INDEX		;ISPITIVANJE REDNOG BROJA ZNAKA KOJEGA T
00000068  01 60 A0 E1  		MOV R6, R1			;ISPITIVANJE INDEKSA ZA KASNIJE 
0000006C  04 14 81 E2  		ADD R1, R1, #4<8 
00000070  00 00 91 E5  		LDR R0, [R1] 
00000074  00 10 A0 E1  		MOV R1, R0			;STAVLJANJE R0 U R1 RADI KASNIJE PROVJERE 
00000078  0F 00 00 EB  		BL LCDWR 
                       		 
0000007C  3C 00 A0 E3  		MOV R0, #3C			;ISPISIVANJE > 3 PUTA 
00000080  03 50 A0 E3  		MOV R5, #3 
                        
00000084  0C 00 00 EB  ISPIS2	BL LCDWR 
00000088  01 50 55 E2  		SUBS R5, R5, #1 
0000008C  FE FF FF 1A  		BNE ISPIS2 
                       		 
00000090  0A 00 A0 E3  		MOV R0, #0A			;ISPIS NA EKRAN 
00000094  08 00 00 EB  		BL LCDWR 
                       	 
                       	 
00000098  00 00 51 E3  		CMP R1, #0			;PROVJERA JE LI ZADNJI CLAN 0 
0000009C  00 10 A0 E3  		MOV R1, #0 
000000A0  01 60 86 12  		ADDNE R6, R6, #1	;POVACANJE INDEXA 
000000A4  44 60 8F 15  		STRNE R6, INDEX 
000000A8  40 10 8F 05  		STREQ R1, INDEX		;AKO JE ZADNJI CLAN 0, INDEKS POSTAJE 
                       		 
                       		 
000000AC  63 40 BD E8  		LDMFD R13!, {R0, R1, R5, R6, R14} 
000000B0  04 F0 5E E2  		SUBS PC, LR, #4 
                       		 
                       		 
000000B4  01 00 2D E9  LCDWR 	STMFD R13!, {R0} 
                       		 
000000B8  7F 00 00 E2  		AND R0, R0, #7F			;SALJI ZNAK 
000000BC  04 00 C3 E5  		STRB R0, [R3, #4] 
                       		 
000000C0  80 00 80 E3  		ORR R0, R0, #80			;DIGNI IMPULS 
000000C4  04 00 C3 E5  		STRB R0, [R3, #4] 
                       		 
000000C8  7F 00 00 E2  		AND R0, R0, #7F			;SPUSTI IMPULS 
000000CC  04 00 C3 E5  		STRB R0, [R3, #4] 
                       		 
000000D0  01 00 BD E8  		LDMFD R13!, {R0}		;KONTEKST 
000000D4  0E F0 A0 E1  		MOV PC, LR 
                       		 
                       		 
000000D8  56 34 12 EF  KRAJ 	SWI 123456 
                        
000000DC  00 FF FF FF  GPIOADR `DW 00,FF,FF,FF 
                        
000000E0  56 02 00 00  TIME `DW 56,02,00,00 
                        
000000E4  00 FE FF FF  RTC `DW 00,FE,FF,FF 
                        
000000E8  00 00 00 00  INDEX `DW 00,00,00,00 
                       		 
                       `ORG 400 
00000400  49 6E 74 65  NIZ `DW 49,6E,74,65,72,6E,61,74,69,6F,6E,61,6C,69,73,61,7
          72 6E 61 74  
          69 6F 6E 61  
          6C 69 73 61  
          74 69 6F 6E  
          00           
                        
