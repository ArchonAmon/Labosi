                       VJ1 `EQU FFFF1000 
                        
                       VJ2 `EQU FFFF2000 
                       VJ2BS `EQU FFFF2004 
                        
                       VJ3 `EQU FFFF3000 
                       VJ3RADIM `EQU FFFF3004 
                       VJ3GOTOV `EQU FFFF3008 
                       VJ3ZAUSTAVI `EQU FFFF300C 
                       		 
                       `ORG 0 
00000000  00 00 81 07  		MOVE 10000, SP 
00000004  0C 00 00 C4  		JP GLAVNI 
                       		 
                       `ORG 8 
00000008  00 02 00 00  `DW 00,02,00,00 
                       		 
                       				 
0000000C  10 00 10 04  GLAVNI	MOVE %B 10000, SR 
00000010  01 00 00 04  		MOVE 1, R0 
00000014  0C 30 0F B8  		STORE R0, (VJ3ZAUSTAVI) 
                       		 
00000018  80 02 00 B0  PETLJA	LOAD R0, (STOP) 
0000001C  00 00 00 08  		OR R0, R0, R0 
00000020  48 02 00 D6  		JR_NZ KRAJ 
                        
00000024  04 20 0F B0  		LOAD R0, (VJ2BS)  ;ISPITIVANJE VJ2 
00000028  00 00 00 08  		OR R0, R0, R0 
0000002C  E8 FF CF D5  		JR_Z PETLJA 
                       		 
00000030  78 02 80 B0  		LOAD R1, (BROJ)	  ;STAVLJANJE OBRADENOG BROJA U VJ2 
00000034  00 20 8F B8  		STORE R1, (VJ2) 
00000038  04 20 8F B8  		STORE R1, (VJ2BS) 
                       		 
0000003C  7C 02 00 B0  		LOAD R0, (BROJAC)  ;BROJAC + 1 
00000040  01 00 00 24  		ADD R0, 1, R0 
00000044  7C 02 00 B8  		STORE R0, (BROJAC) 
00000048  CC FF 0F D4  		JR PETLJA 
                       		 
                       		 
                       		 
                       		 
                       `ORG 200 
00000200  00 00 00 88  		PUSH R0	  ;KONTEKST 
00000204  00 00 20 00  		MOVE SR, R0 
00000208  00 00 00 88  		PUSH R0 
                       		 
0000020C  04 30 0F B8  		STORE R0, (VJ3RADIM)   ;OBRADA PODATAKA 
00000210  00 10 0F B0  		LOAD R0, (VJ1)		   ;UZIMANJE BROJA IZ VJ1 
00000214  00 00 00 08  		OR R0, R0, R0 
00000218  48 00 40 D4  		JR_N NEG 
                       		 
0000021C  00 00 00 88  		PUSH R0 
00000220  48 02 00 CC  		CALL OBRADI			   ;POZIVANJE POTPROGRAMA 
00000224  04 00 F0 27  		ADD SP, 4, SP 
00000228  78 02 00 B8  		STORE R0, (BROJ) 
                       		 
0000022C  7C 02 80 B0  		LOAD R1, (BROJAC)	   ;SPREMANJE BROJACA NA VJ3 
00000230  00 30 8F B8  		STORE R1, (VJ3) 
                       		 
00000234  00 00 00 80  DALJE	POP R0				   ;KONTEKST 
00000238  00 00 10 00  		MOVE R0, SR 
0000023C  00 00 00 80  		POP R0 
00000240  08 30 0F B8  		STORE R0, (VJ3GOTOV) 
00000244  01 00 00 D8  		RETI 
                       		 
                       		 
00000248  00 00 80 88  OBRADI  PUSH R1 
                        
0000024C  08 00 70 B4  		LOAD R0, (SP+8) 
00000250  01 00 00 34  		SUB R0, 1, R0 
00000254  02 00 80 04  		MOVE 2, R1 
00000258  00 00 10 50  		SHL R1, R0, R0 
                       		 
0000025C  00 00 80 80  		POP R1 
00000260  00 00 00 D8  		RET 
                       		 
00000264  80 02 80 B8  NEG 	STORE R1, (STOP) 
00000268  C8 FF 0F D4  		JR DALJE 
                       		 
                       		 
0000026C  00 00 00 04  KRAJ    MOVE 0, R0 
00000270  0C 30 0F B8  		STORE R0, (VJ3ZAUSTAVI) 
00000274  00 00 00 F8  		HALT 
                       		 
00000278  00 00 00 00  BROJ `DW 00,00,00,00 
0000027C  00 00 00 00  BROJAC `DW 00,00,00,00 
00000280  00 00 00 00  STOP `DW 00,00,00,00 
                        
