; *********************************************************************
; * IST-UL
; * Grupo05
; *
; * Becam Tombia - 1110640
; * Diogo Foliao - 1102970
; * Francisco cardoso - 1109823
; * 
; * Descricao: Este projeto consiste na criacao de um jogo inspirado no classico
; * jogo do Pac-Man. 
; *********************************************************************

; **********************************************************************
; * Constantes
; **********************************************************************

; **********************************
; * Constantes globais do programa
; **********************************

TAM_PILHA	EQU	200H
MENOS_DEZ	EQU	0FFF6H
MENOS_UM 	EQU	0FFFFH			; Valor -1
ZERO		EQU	0000H			; Valor 0
UM		EQU	0001H			; Valor 1
DOIS		EQU	0002H			; Valor 2
TRES		EQU     0003H			; Valor 3
QUATRO		EQU 	0004H			; Valor 4
CINCO		EQU 	0005H			; Valor 5
SETE  		EQU 	0007H                   ; Valor 7
A_H 		EQU	000AH			; Valor da letra A do teclado 
B_H		EQU 	000BH			; Valor da letra B do teclado
C_H		EQU 	000CH			; Valor da letra C do teclado
D_H		EQU	000DH 			; Valor da letra D do teclado
E_H		EQU	000EH			; Valor da letra E do teclado
F_H		EQU 	000FH			; Valor da letra F do teclado
SCREEN_CONTROL	EQU 	6000H 		        ; Endereco do inicio dos comandos do media center
SELECT_BG_IMAGE EQU	SCREEN_CONTROL + 0042H  ; Endereco do comando para especificar a imagem de fundo
SELECT_SOUND	EQU 	SCREEN_CONTROL + 005AH  ; Endereco para colocar um video ou som especificado
SELECT_VID_SOUND_INFINITE	EQU 	SCREEN_CONTROL + 005CH  ; Endereco para colocar um video ou som especificado
SELECT_FG_IMAGE EQU 	SCREEN_CONTROL + 0046H  ; Endereco para selecionar imagem frontal
STOP_GIVEN_SOUND EQU 	SCREEN_CONTROL + 005EH  ; Endereco para selecionar imagem frontal
START_GIVEN_SOUND EQU 	SCREEN_CONTROL + 0064H  ; Endereco para selecionar imagem frontal
APAGAR_FG_IMAGE EQU 	SCREEN_CONTROL + 0044H  ; Endereco para selecionar imagem frontal
MASC_DETETA_ESQ	EQU	0001H			; Mascara para detetar teclas que andam para a esquerda (0000 0001)
MASC_DETETA_DIR	EQU	0004H			; Mascara para detetarteclas que andam para a direita (0000 0100)
MASC_DETETA_CIMA	EQU	0010H		; Mascara para detetar teclas que andam para cima (0001 0000)
MASC_DETETA_BAIXO	EQU	0040H		; Mascara para detetar teclas que andam para baixo (0100 0000)

; **********************************
; * Constantes para o contador
; **********************************
Mascara_nibble_L	EQU 	000FH          			; isola o 4 nibble 0001 , representa o conjunto isolado
CONTADOR		EQU		3FF0H                   ; Endereco que contem o valor do contador   
CONVERSAO_UNIDADES	EQU     0007H
Mascara_nibble_H 	EQU 	0010H 
NOVE        		EQU		0009H           		; valor 9
letra_hexadecimal	EQU     000AH
MASCARA_nibble_HH	EQU     0100H
OITO			EQU		0008H
CONTAGEM		EQU     3FE0H                   ; guarda o valor da interrupcao 
SIX			EQU 	0006H
; **********************************
; * Constantes para desenhar no ecra
; **********************************

DEFINE_LINHA    EQU 	SCREEN_CONTROL + 000AH  ; Endereco do comando para definir a linha
DEFINE_COLUNA  	EQU 	SCREEN_CONTROL + 000CH  ; Endereco do comando para definir a coluna
DEFINE_PIXEL    EQU	SCREEN_CONTROL + 0012H  ; Endereco do comando para escrever um pixel
OBTEM_PIXEL	EQU	SCREEN_CONTROL + 0010H	; Endereco do comando para obter o valor de um pixel
DEFINE_CANETA	EQU	SCREEN_CONTROL + 0014H	; Endereco do comando para defenir a cor da caneta
AUTO_INCREMENTO	EQU	SCREEN_CONTROL + 10H	; Endereco do comando para modificar o auto incremento
ALTERA_16_PIX	EQU	SCREEN_CONTROL + 1EH	; Endereco do comando defenir 16 pixels
APAGA_AVISO     EQU 	SCREEN_CONTROL + 0040H  ; Endereco do comando para apagar o aviso de nenhum cenario selecionado
REPRODUZ_SOM     EQU 	SCREEN_CONTROL + 005CH  ; Endereco do comando parareproduzir som continuamente
APAGA_ECRA	EQU 	SCREEN_CONTROL + 0002H  ; Endereco do comando para apagar todos os pixels ja desenhados

LINHA_MID	EQU     15                      ; Linha a meio do ecra
COLUNA_MID 	EQU	31                      ; Coluna a meio do ecra
LAST_LINHA	EQU	31			; ultima linha do ecra
LAST_COLUNA	EQU	63			; ultima coluna do ecra
L_SPAWN_COLUNA  EQU     1                       ; Coluna do lado esquedo para o spawn do fantasma  
R_SPAWN_COLUNA  EQU     59                      ; Coluna do lado esquedo para o spawn do fantasma
LINHA_INICIAL	EQU	14			; Linha da posição inical do pacman
COLUNA_INICIAL	EQU	28			; Coluna da posição inical do pacman
PRIMEIRA_LINHA	EQU	0			; Primeira linha do media center
ULTIMA_LINHA	EQU	31			; Ultima linha do media center
PRIMEIRA_COLUNA	EQU	0			; Primeira coluna do media center
ULTIMA_COLUNA	EQU	63			; Ultima coluna do media center


;******* NOTA - So os fantasmas e a a cor dos pixels de colisao tem o ultimo bit a um *******

TOTAL_PIXELS_BLOQUEIO_SPAWN_FANT_E_CAIXA	EQU	22		; O numero de pixels de bloqueio que existem na caixa e nos spawns dos fantasmas
TOTAL_PIXELS_BLOQUEIO_BORDAS_V	EQU	12	; O numero de pixels de bloqueio que existem nas bordas verticais do media center
TOTAL_PIXELS_BLOQUEIO_BORDAS_H	EQU	32	; O numero de pixels de bloqueio que existem nas bordas horizontais do media center
TOTAL_PIXELS_NV_2				EQU	40
COR_PSEUDO_INVISIVEL			EQU	0001H			; Cor dos pontos de bloqueio de movimento, (tudo a 0, menos o ultimo bit, que fica a um)
MASCARA_FIRST_BIT				EQU	0001H			; Mascara para isolar o ultimo bit de uma WORD


LARGURA_PAC	EQU	0004H		        ; Largura do pacman
ALTURA_PAC	EQU	0005H                   ; Altura_PACMAN
COR_PAC		EQU     0FFF0H		        ; Cor do PACMAN: amarelo em ARGB (opaco, verde e vermelho no maximo, e azul a 0)


LARGURA_PAC_PWR_UP	EQU 	4					; Largura do pacman depois de ficar com powerup 
ALTURA_PAC_PWR_UP	EQU 	4					; Altura  do pacman depois de ficar com powerup
COR_PWR_UP		EQU	0F0FEH				; Cor do PACMAN depois de ficar com powerup

ALTURA_FANT	EQU 	0004H                   ; Largura dos fantasmas
LARGURA_FANT	EQU 	0004H                   ; Altura dos fantasmas
COR_FANT	EQU     0F0F1H                  ; Cor dos fantasmas: verde em ARGB (opaco e verde no maximo, vermelho a 0 e azul a 1) (ultimo bit e 1)


ALTURA_REB	EQU	0004H			; altura do rebucado
LARGURA_REB	EQU	0004H			; largura do rebucado
COR_REB		EQU	0FF06H			; cor do rebucado (alpha e vermelho a F, azul a 6)


ALTURA_SMALL_EXP	EQU	0003H			; altura do frame pequeno da explosão
LARGURA_SMALL_EXP	EQU	0003H			; largura do frame pequeno da explosão
COR_EXP			EQU	0F0FFH			; Cor dos frames da explosão (alpha, green e blue a F, vermelho a 0)
ALTURA_BIG_EXP		EQU	0005H			; altura do frame grande da explosão
LARGURA_BIG_EXP		EQU	0005H			; largura do frame grande da explosão

; **************************************************
; * Constantes a serem utilizadas na rotina teclado
; **************************************************

DISPLAYS	EQU	0A000H                  ; Endereco dos displays de 7 segmentos(periferico POUT-1)
TEC_LIN		EQU	0C000H                  ; Endereco das linhas do teclado (periferico POUT-2)
TEC_COL		EQU	0E000H                  ; Endereco das colunas do teclado (periferico PIN)
LINHA		EQU	1111H                   ; O byte de menor peso possui a linha a testar (0001 0001 0001 0001)
MASCARA		EQU	000FH                   ; Para isolar os 4 bits de menor peso, ao ler as colunas do teclado

; #######################################################################
; * ZONA DE DADOS 
; #######################################################################


PLACE	2000H			                ; Endereco inical dos dados dos objetos

posicao_pacman:					; Tabela que indica a posição corrente do pacman NAO E INICIALIZADA NO INICIO DO PROGRAMA!!!!!!!!!!!!!!!!!!!
	WORD	LINHA_INICIAL, COLUNA_INICIAL

posicao_fantasma:
	WORD	ZERO				; Comecamos com 1 fantasma	
	WORD	LINHA_MID, ZERO	+ DOIS		; Fantasma 1
	WORD	LINHA_MID, LAST_COLUNA - TRES	; Fantasma 2
	WORD	LINHA_MID, ZERO + DOIS		; Fantasma 3
	WORD	LINHA_MID, LAST_COLUNA	- TRES	; Fantasma 4

POWER_UP:
	WORD	ZERO				; Endereco que controla se o pacman tem o powerup

UP_DOWN:					; Endereço que controla o movimento vertical do pacman (1: Cima ; -1 Baixo)
	WORD	ZERO

RIGHT_LEFT:					; Endereço que controla o movimento horizontal do pacman (1: Direita ; -1: Esquerda)
	WORD	ZERO

incrementa_tempo:
	WORD	ZERO				; Endereco que e controlado pela interrupcao. Se estiver a 1, o contador incrementa 1 segundo

movimenta_pacman:
	WORD	ZERO				; Endereco que e controlado pela interrupcao 0. Se estiver a 1, o objeto em questao pode andar
	
movimenta_fantasmas:
	WORD	ZERO				; Controla se os fantasma podem mover

ha_colisao:
	WORD	ZERO				; Ve se ha colisao
	

vitoria_estado:
	WORD 	ZERO				; Averigua se os 4 robucados foram comidos

explosao:
	WORD	ZERO

rebucados_info:
	WORD	QUATRO						; Numero de robucados disponiveis para serem apanhados
    	WORD 	ZERO + DOIS, ZERO + DOIS			; posicao robucado 1
	WORD	ZERO + DOIS, LAST_COLUNA - CINCO		; posicao robucado 2
	WORD	LAST_LINHA - CINCO, ZERO + DOIS			; posicao robucado 3
	WORD	LAST_LINHA - CINCO, LAST_COLUNA - CINCO		; posicao robucado 4


DEF_FANTASMA:                                   		; Tabela que define o PACMAN (altura, largura, cor, pixels)
	WORD	ALTURA_FANT, LARGURA_FANT
	WORD	0, COR_FANT, COR_FANT, 0
	WORD	COR_FANT, COR_FANT, COR_FANT, COR_FANT
	WORD	COR_FANT, COR_FANT, COR_FANT, COR_FANT
	WORD	COR_FANT, 0, 0, COR_FANT

DEF_REBUCADO:
	WORD	ALTURA_REB, LARGURA_REB
	WORD	COR_REB, 0, 0, COR_REB
	WORD	0, COR_REB, COR_REB, 0
	WORD	0, COR_REB, COR_REB, 0
	WORD	COR_REB, 0, 0, COR_REB

DEF_SMALL_EXP:
	WORD	ALTURA_SMALL_EXP, LARGURA_SMALL_EXP
	WORD	COR_EXP, 0, COR_EXP
	WORD	0, COR_EXP, 0
	WORD	COR_EXP, 0, COR_EXP

DEF_BIG_EXP:
	WORD	ALTURA_BIG_EXP, LARGURA_BIG_EXP
	WORD	0, COR_EXP, 0, COR_EXP, 0
	WORD	COR_EXP, 0, COR_EXP, 0, COR_EXP
	WORD	0, COR_EXP, 0, COR_EXP, 0
	WORD	COR_EXP, 0, COR_EXP, 0, COR_EXP
	WORD	0, COR_EXP, 0, COR_EXP, 0

DEF_PACMAN_PWR_NOMOUTH:
	WORD	ALTURA_PAC_PWR_UP, LARGURA_PAC_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP


DEF_PACMAN_PWR_RIGHT:
	WORD	ALTURA_PAC_PWR_UP, LARGURA_PAC_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, 0, 0
	WORD	COR_PWR_UP, COR_PWR_UP, 0, 0
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP

DEF_PACMAN_PWR_LEFT:
	WORD	ALTURA_PAC_PWR_UP, LARGURA_PAC_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	0, 0, COR_PWR_UP, COR_PWR_UP
	WORD	0, 0, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP

DEF_PACMAN_PWR_UP:
	WORD	ALTURA_PAC_PWR_UP, LARGURA_PAC_PWR_UP
	WORD	COR_PWR_UP, 0, 0, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP

DEF_PACMAN_PWR_DOWN:
	WORD	ALTURA_PAC_PWR_UP, LARGURA_PAC_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, COR_PWR_UP, COR_PWR_UP, COR_PWR_UP
	WORD	COR_PWR_UP, 0, 0, COR_PWR_UP

DEF_PACMAN_PWR:
	WORD	DEF_PACMAN_PWR_RIGHT
	WORD	DEF_PACMAN_PWR_LEFT
	WORD	DEF_PACMAN_PWR_UP
	WORD	DEF_PACMAN_PWR_DOWN
	WORD	DEF_PACMAN_PWR_NOMOUTH

DEF_PACMAN_NOMOUTH_UP_DOWN:
	WORD	 LARGURA_PAC, ALTURA_PAC
	WORD	ZERO, COR_PAC, COR_PAC, ZERO
	WORD	COR_PAC, COR_PAC, COR_PAC, COR_PAC
	WORD	COR_PAC,COR_PAC,COR_PAC, COR_PAC
	WORD	COR_PAC, COR_PAC, COR_PAC, COR_PAC
	WORD	ZERO, COR_PAC, COR_PAC, ZERO

DEF_PACMAN_NOMOUTH:
	WORD	ALTURA_PAC, LARGURA_PAC
	WORD	ZERO, COR_PAC, COR_PAC, ZERO
	WORD	COR_PAC, COR_PAC, COR_PAC, COR_PAC
	WORD	COR_PAC,COR_PAC,COR_PAC, COR_PAC
	WORD	COR_PAC, COR_PAC, COR_PAC, COR_PAC
	WORD	ZERO, COR_PAC, COR_PAC, ZERO

DEF_PACMAN_RIGHT:					; Tabela que define o PACMAN (altura, largura, cor, pixels)
	WORD	ALTURA_PAC, LARGURA_PAC
	WORD	0, COR_PAC, COR_PAC, 0
	WORD	COR_PAC, COR_PAC, COR_PAC, COR_PAC
	WORD	COR_PAC, 0, 0, 0
	WORD	COR_PAC, COR_PAC, COR_PAC, COR_PAC
	WORD	0, COR_PAC, COR_PAC, 0

DEF_PACMAN_LEFT:
	WORD	ALTURA_PAC,LARGURA_PAC
	WORD	ZERO,COR_PAC,COR_PAC,ZERO
	WORD	COR_PAC,COR_PAC,COR_PAC,COR_PAC
	WORD	ZERO,ZERO,ZERO,COR_PAC
	WORD	COR_PAC,COR_PAC,COR_PAC,COR_PAC
	WORD	ZERO, COR_PAC, COR_PAC, ZERO

DEF_PACMAN_UP:
	WORD	LARGURA_PAC,ALTURA_PAC
	WORD	ZERO,COR_PAC,ZERO,COR_PAC,ZERO
	WORD	COR_PAC,COR_PAC,ZERO,COR_PAC,COR_PAC
	WORD	COR_PAC,COR_PAC,ZERO,COR_PAC,COR_PAC
	WORD	ZERO,COR_PAC,COR_PAC,COR_PAC,ZERO

DEF_PACMAN_DOWN:
	WORD	LARGURA_PAC, ALTURA_PAC
	WORD	ZERO,COR_PAC,COR_PAC,COR_PAC,ZERO
	WORD	COR_PAC,COR_PAC,ZERO,COR_PAC,COR_PAC
	WORD	COR_PAC,COR_PAC,ZERO,COR_PAC,COR_PAC
	WORD	ZERO,COR_PAC,ZERO,COR_PAC,ZERO
	
DEF_PACMAN:
	WORD	DEF_PACMAN_RIGHT
	WORD	DEF_PACMAN_LEFT
	WORD	DEF_PACMAN_UP
	WORD	DEF_PACMAN_DOWN
	WORD	DEF_PACMAN_NOMOUTH
	WORD	DEF_PACMAN_NOMOUTH_UP_DOWN


DEF_NIVEL_2:

; O primeiro endereco da tabela indica quantos pixels de bloqueio existem nestas bordas
; Os restantes enderecos indicam as posicoes (linha,coluna) de cada pixel de bloqueio

	WORD	TOTAL_PIXELS_NV_2



	WORD	0,0
	WORD	0,4
	WORD	0,8
	WORD	0,12
	WORD	0,16
	WORD	0,20
	WORD	0,24
	WORD	0,27
	WORD	3,27
	WORD	6,27
	WORD	6,31
	WORD	6,34
	WORD	0,36
	WORD	3,36
	WORD	6,36
	WORD	0,40
	WORD	0,44
	WORD	0,48
	WORD	0,52
	WORD	0,56
	WORD	0,60

	WORD	31,0
	WORD	31,4
	WORD	31,8
	WORD	31,12
	WORD	31,16
	WORD	31,20
	WORD	31,24
	WORD	31,27
	WORD	29,27
	WORD	26,27
	WORD	26,36
	WORD	29,36
	WORD	31,36
	WORD	31,40
	WORD	31,44
	WORD	31,48
	WORD	31,52
	WORD	31,56
	WORD	31,60

;	Tabela que deine os pixels de bloqueio de movimento para os obstaculos do nivel 2

DEF_BORDAS_HORIZONTAIS:

; O primeiro endereco da tabela indica quantos pixels de bloqueio existem nestas bordas
; Os restantes enderecos indicam as posicoes (linha,coluna) de cada pixel de bloqueio

	WORD	TOTAL_PIXELS_BLOQUEIO_BORDAS_H

; Bordas Horizontais
	WORD	0,0
	WORD	0,4
	WORD	0,8
	WORD	0,12
	WORD	0,16
	WORD	0,20
	WORD	0,24
	WORD	0,28
	WORD	0,32
	WORD	0,36
	WORD	0,40
	WORD	0,44
	WORD	0,48
	WORD	0,52
	WORD	0,56
	WORD	0,60

	WORD	31,0
	WORD	31,4
	WORD	31,8
	WORD	31,12
	WORD	31,16
	WORD	31,20
	WORD	31,24
	WORD	31,28
	WORD	31,32
	WORD	31,36
	WORD	31,40
	WORD	31,44
	WORD	31,48
	WORD	31,52
	WORD	31,56
	WORD	31,60

;	Tabela que deine os pixels de bloqueio de movimento para as bordas verticais do media center

DEF_BORDAS_VERTICAIS:

; O primeiro endereco da tabela indica quantos pixels de bloqueio existem nestas bordas
; Os restantes enderecos indicam as posicoes (linha,coluna) de cada pixel de bloqueio

	WORD	TOTAL_PIXELS_BLOQUEIO_BORDAS_V
; Borda vertical (direita)
	WORD	3,63
	WORD	7,63
	WORD	11,63
	WORD	23,63
	WORD	27,63
	WORD	31,63

; Borda vertical (esquerda)
	WORD	3,0
	WORD	7,0
	WORD	11,0
	WORD	23,0
	WORD	27,0
	WORD	31,0

; Tabela que deine os pixels de bloqueio de movimento para o primeiro DEF_SPAWN_FANTASMAS_CAIXA
DEF_SPAWN_FANTASMAS_CAIXA:

; O primeiro endereco da tabela indica quantos pixels de bloqueio existem neste nivel
; Os restantes enderecos indicam as posicoes (linha,coluna) de cada pixel de bloqueio
; Esta tabela nao tem em conta as bordas laterais do ecra, para desenhar isso, e usada outra tabela

	WORD	TOTAL_PIXELS_BLOQUEIO_SPAWN_FANT_E_CAIXA
; Spawn esquedo dos fantasmas
	WORD	11,0
	WORD	11,4
	WORD	13,4
	WORD	20,0
	WORD	20,4
	WORD	18,4


; Caixa aberta
	WORD	19,25
	WORD	19,27
	WORD	16,25
	WORD	12,25
	WORD	12,27
	WORD	19,38
	WORD	19,36
	WORD	16,38
	WORD	12,38
	WORD	12,36

; Spawn direito dos fantasmas
	WORD	11,63
	WORD	11,59
	WORD	13,59
	WORD	20,59
	WORD	20,63
	WORD	18,59



; **********************************************************************
; * Codigo principal do programa
; **********************************************************************

; **********************************************************************
; * Inicializacao da pilha
; **********************************************************************

pilha:	STACK	TAM_PILHA		; Reserva memoria para a pilha
apos_pilha: 

tab_inter:				; Tabela de interrupcoes
		WORD	rot_int_0		
		WORD	rot_int_1	
		WORD	rot_int_2
		WORD 	rot_int_3

;***********************************************************************
; Corpo principal do programa
;***********************************************************************

PLACE	0000H

principal:
	MOV	SP, apos_pilha			; Inicializamos a pilha
	MOV 	BTE, tab_inter			; Inicializamos a tabela de interrupcoes
	MOV	R8, ZERO                        ; O registo R8 vai guardar a tecla premida no ciclo anterior
	MOV	R10 , ZERO                      ;  iniciar registo com o valor do contador a 0
	MOV	[CONTADOR],R10			; iniciar o valor do contaodr a zero

; **********************************************************************
; * Rotina: menu_principal 
; * Esta rotina consiste numa ronda de jogo. Quando acaba, o utilizador pode 
; * jogar de novo.
; * 	{}
; * Saida:
; *	{}	
; **********************************************************************

CALL	menu_principal			; Iniciamos o jogo
JMP	menu_principal			; Repete jogo depois de vitoria/derrota

menu_principal:
	MOV	R1, ZERO
	MOV	[APAGA_ECRA], R1
	CALL	teclado				; Tecla pressionada vem em R1
	MOV 	R2, C_H				; Tecla que começa o jogo
	MOV	R3, CINCO			; Selecionamos imagem start game
	MOV	[SELECT_BG_IMAGE], R3		; Colocamos a imagem de fundo
	CMP	R1, R2				; Tecla pressionada é C?
	JNZ	menu_principal			; Se nao, nao muda imagem

inicio_jogo:
	MOV	R1, TRES			; Seleciona som de fundo
	MOV 	[SELECT_VID_SOUND_INFINITE], R1	; Reproduz som de fundo
	MOV	R1, ZERO			
	MOV	[APAGA_ECRA], R1		; Apaga ecra


reset_fantasma:
; DAR RESET NOS FANTASMAS
; Reset numero de fantasmas
	MOV 	R5, posicao_fantasma				; Posicao da tabela dos fantasma
	MOV 	R1, ZERO					; Numero de fantasmas apos morte
	MOV	[R5], R1					; Reset no numero de fantasmas
	MOV 	R2, LINHA_MID					; Linha do meio
	MOV	R3, DOIS					; Spawn esquerda
	MOV	R4, LAST_COLUNA - DOIS				; Spawn direita

; Reset Posicoes
	ADD	R5, DOIS					; Fantasma 1 X
	MOV	[R5], R2					; Proxima coordenada
	ADD	R5, DOIS					; Fantasma 1 Y
	MOV	[R5], R3					; Proximo robucado
	ADD	R5, DOIS					; Fantasma 2 X
	MOV	[R5], R2					; Proxima coordenada
	ADD	R5, DOIS					; Fantasma 2 Y
	MOV	[R5], R4					; Proxima coordenada
	ADD	R5, DOIS					; Fantasma 3 X
	MOV	[R5], R2					; Proximo robucado
	ADD	R5, DOIS					; Fantasma 3 Y
	MOV	[R5], R3					; Proxima coordenada
	ADD	R5, DOIS					; Fantasma 4 X
	MOV	[R5], R2					; Proximo robucado
	ADD	R5, DOIS					; Fantasma 4 Y
	MOV	[R5], R4


pacman_spawn:
	MOV	R1, LINHA_INICIAL		; Linha do centro do ecrã 
	MOV	R2, COLUNA_INICIAL		; Coluna do do centro do ecrã
	MOV	[posicao_pacman], R1	; Inicializa a posicao inical (linha)
	MOV	[posicao_pacman + DOIS], R2	; Inicializa a posicao inical (coluna)
	;(o +2 é necessario para aceder ao endereco seguinte, porque uma WORD sao 2 bytes)

	MOV	R3,  DEF_PACMAN_RIGHT                 ; endereco da tabela que define o pacman
	CALL	desenha_objeto                  ; Desenha o pacman no centro do ecra
	
rebucados_spawn:
	MOV 	R6, rebucados_info		; Posicao do n de rebucados
	MOv	R1, QUATRO			; Numero de rebucados
	MOV 	[R6], R1 			; Numero de rebucados
	MOV 	R5, [R6]			; Auxialiar que guarda R1
	MOV 	R3, DEF_REBUCADO

	MOV	R2, DOIS			; Auxiliar
	MOV	R4, LAST_COLUNA - CINCO	 	; Auxiliar
	MOV	R7, LAST_LINHA - CINCO

	ADD	R6, DOIS		; Proximo endereco
	MOV	[R6], R2 		; Posicao X
	ADD	R6, DOIS		; Proximo endereco
	MOV	[R6], R2		; Posicao Y
	ADD	R6, DOIS		; Proximo rebucado
	MOV	[R6], R2 		; Posicao X
	ADD	R6, DOIS		; Proximo endereco
	MOV	[R6], R4		; Posicao Y
	ADD	R6, DOIS
	MOV	[R6], R7 		; Posicao X
	ADD	R6, DOIS		; Proximo endereco
	MOV	[R6], R2		; Posicao Y
	ADD	R6, DOIS
	MOV	[R6], R7 		; Posicao X
	ADD	R6, DOIS		; Proximo endereco
	MOV	[R6], R4		; Posicao Y

	MOV	R6, rebucados_info
	ADD	R6, R2
spawn_rebucados:
	MOV 	R1, [R6]			; Posicao da linha
	ADD	R6, DOIS			; Memoria a seguir
	MOV 	R2, [R6]			; Posicao da coluna 
	CALL 	desenha_objeto			; Rotina que leva R1, R2 e R3
	ADD 	R6, DOIS
	SUB 	R5, UM				; Loop 4 robucados
	JNZ	spawn_rebucados			; Desenhamos outro robucado


	EI0					; Ativa interrupcao 0
	EI1					; Ativa interrupcao 1
	EI2					; Ativa interrupcao 2
	EI3					; Ativa	interrupcao 3
	EI					; Ativa interrupcoes (geral)



CALL	desenha_nivel_2
ciclo_principal:	                        ; Loop principal do programa
	CALL	teclado	                        ; Rotina que deteta a tecla pressionada
	CALL	processa_tecla			; Rotina que invocara outra rotina de cordo com a tecla pressionada 
	CMP	R10, UM				; Vemos se algum fantasma tocou no pacman
	JZ	principal		; Tela de fim de jogo
	CALL	contador                        ; Rotina responsavel por alterar o valor do Contador
	MOV	R8, R1				; Guardamos a tecla pressionada neste ciclo em R8 para ser comparada no proximo
	CALL	movimento_pacman		; Movimento Pac-man	
	CALL	mete_fantasmas			; Movimenta fantasmas
	CALL	verifica_derrota		; Verifica se algum fantasma tocou no pacman. Retorna em R10 essa informacao
	CMP	R10, UM				; Vemos se algum fantasma tocou no pacman
	JZ	fim_jogo_derrota		; Tela de fim de jogo
	CALL 	verifica_vitoria		; Verfica se algum robucado foi comido. Retorna em R10 se ja se ganhou o jogo
	CMP	R10, UM				; Vemos se esta ativo
	JZ	vitoria_jogo			; Faz acao de vitoria
	JMP	ciclo_principal			; Voltamos a correr o ciclo

desenha_nivel_1:
	PUSH 	R1
	MOV		R1,	ZERO
	MOV		[SELECT_BG_IMAGE], R1		; Colocamos a imagem de fundo
	MOV 	R1, DEF_SPAWN_FANTASMAS_CAIXA
	CALL 	desenha_nivel
	MOV		R1,	DEF_BORDAS_HORIZONTAIS
	CALL 	desenha_nivel
	MOV		R1,	DEF_BORDAS_VERTICAIS
	CALL 	desenha_nivel
	POP		R1
	RET

desenha_nivel_2:
	PUSH	R1
	MOV	R1,	6						; Imagem 6 do media center
	MOV	[SELECT_BG_IMAGE], R1		; Colocamos a imagem de fundo
	MOV 	R1, DEF_SPAWN_FANTASMAS_CAIXA
	CALL 	desenha_nivel
	MOV		R1,	DEF_BORDAS_VERTICAIS
	CALL 	desenha_nivel

	MOV		R1,	DEF_NIVEL_2
	CALL 	desenha_nivel
	POP		R1
	RET

; ***************************************************************************
; * Fim do jogo e definido
; **************************************************************************
fim_jogo_derrota:
	DI
	MOV	R5, ZERO			; Som derrota
	MOV	[SELECT_SOUND], R5 		; Reproduz
	MOV 	R5, ZERO			; Estado ja houve colisao
	MOV	[ha_colisao], R5		; Metemos que ja houve colisao
	MOV	R3, DOIS			; Selecionamos imagem start game
	MOV	[APAGA_ECRA], R5		; Apagamos ecra
	MOV	[SELECT_BG_IMAGE], R3		; Colocamos a imagem de fundo
	MOV	R5, TRES			; Seleciona som de fundo
	MOV	[STOP_GIVEN_SOUND], R5			; Pausa som


loop_fim_jogo:
	CALL	teclado				; Tecla pressionada vem em R1
	MOV	R2, F_H				; Tecla para sair
	CMP	R1, R2				; Tecla pressionada é C?
	JZ	retorna_principal		; Voltamos a jogar
	JMP	loop_fim_jogo		


; ***************************************************************************
; * Vitoria e definida
; **************************************************************************
vitoria_jogo:
	DI
	MOV	R5, QUATRO			; Som derrota
	MOV	[SELECT_SOUND], R5 		; Reproduz
	MOV 	R5, ZERO			; Estado ja houve vitoria
	MOV	[vitoria_estado], R5		; Metemos que ja houve vitoria
	MOV	R3, UM				; Selecionamos imagem start game
	MOV	[APAGA_ECRA], R5		; Apagamos ecra
	MOV	[SELECT_BG_IMAGE], R3		; Colocamos a imagem de fundo
	MOV	R5, TRES			; Seleciona som de fundo
	MOV	[STOP_GIVEN_SOUND], R5			; Pausa som



loop_vitoria:
	CALL	teclado				; Tecla pressionada vem em R1
	MOV	R2, F_H				; Tecla para sair 
	CMP	R1, R2				; Tecla pressionada é F?
	JZ	retorna_principal	 	; Voltamos a jogar
	JMP	loop_vitoria				


retorna_principal:
	RET

; **********************************************************************
; * Rotinas de interrupcao
; **********************************************************************

; **********************************************************************
; * Rotina: rot_int_0 
; * Rotina de interrupcao que regula o andar dos fantasmas. 
; * Altera na memoria de 0 para 1 o endereco indicado. 
; **********************************************************************

rot_int_0:
	PUSH	R3				; Guarda o valor de R3
	MOV	R3, UM				; Permitimos andar
	MOV	[movimenta_fantasmas], R3	; Guardamos que os fantasmas podem andar
	POP	R3				; Recuperamos o valor de R3
	RFE					; Retornamos da interrupcao

; **********************************************************************
; * Rotina: rot_int_1
; * Rotina de interrupcao que regula o tempo passado no contador. 
; * Altera na memoria de 0 para 1 o endereco indicado. 
; **********************************************************************

rot_int_1:
	PUSH    R10			; Guarda o valor de R10
	MOV     R10, UM			; permitimos contar    
	MOV     [CONTAGEM], R10		; Guardamos na memoria que podemos contar
	POP     R10			; Recuperamos o valor de R10
	RFE				; Retornamos da interrupcao

; **********************************************************************
; * Rotina: rot_int_2
; * Rotina de interrupcao que regula explosão
; **********************************************************************

rot_int_2:
	RFE

; **********************************************************************
; * Rotina: rot_int_3 
; * Rotina de interrupcao que regula o andar do pacman. 
; * Altera na memoria de 0 para 1 o endereco indicado. 
; **********************************************************************

rot_int_3:
	PUSH 	R3				; Guarda o valor de R3
	MOV	R3, UM				; Permitimos andar
	MOV	[movimenta_pacman], R3		; Guardamos na memoria que pode andar
	POP	R3				; Recuperamos o valor de R3
	RFE					; Retornamos da interrupcao

; **********************************************************************
; * Rotina: Teclado
; * Esta rotina le as 4 linhas do teclado e deteta a tecla pressionada
; * Quando tal acontece, a rotina e interrompida
; * Retorna com o valor da mesma tecla em R1
; * convertida no valor de jogo (0H a FH)
; * Argumentos:
; * 	{}
; * Saida:
; *	--> R1 - Valor da tecla em formato de jogo
; * 	--> R9 - Valor da tecla em formato de leitura do teclado
; **********************************************************************

teclado:
	PUSH	R2
	PUSH	R3 
	PUSH	R4
	PUSH	R5
	PUSH	R6
	PUSH	R7

; inicializacoes da rotina teclado

	MOV	R2, TEC_LIN                     ; Endereco do periferico das linhas
	MOV	R3, TEC_COL                     ; Endereco do periferico das colunas
	MOV	R4, DISPLAYS                    ; Endereco do periferico dos displays
	MOV	R5, MASCARA                     ; Para isolar os 4 bits de menor peso, ao ler as colunas do teclado
	MOV	R1, LINHA                       ; Comecamos por testar a linha 1  
	MOV	R6, ZERO                        ; Variavel auxiliar para fazer um for de 4 iter.

corre_linhas:
	ROL	R1, UM                          ; Vemos a linha seguinte armazenada em R1
	CMP	R6, QUATRO                      ; Vemos quantas linhas ja lemos
	JNN	nao_ha_tecla                    ; Se o contador nao for {0, 1, 2, 3}, ja lemos as 4 linhas
	MOV	R7, R1                          ; Guardamos a linha lida num registo auxiliar
	AND	R7, R5                          ; Isolamos os bits de menor peso que corresponde � linha
	MOVB	[R2], R1                        ; Escreve no periferico de saida (linhas)
	MOVB	R0, [R3]                        ; Le do periferico de entrada (colunas)
	AND	R0, R5                          ; Elimina bits para alem dos bits 0-3  
	ADD	R6, UM                          ; Passamos a linha seguinte 
	CMP	R0, ZERO                        ; Ha tecla premida?
	JZ	corre_linhas                    ; Se nao existir tecla nesta linha, vemos a proxima
	MOV	R1, R7                          ; Colocar o valor da linha de novo em R1
	SHL	R1, QUATRO                      ; Coloca linha no nibble high
	OR	R1, R0                          ; Junta a coluna no nibble low
	MOV	R9, R1
	CALL	converte_tecla                  ; Convertemos o valor da tecla na sua representacao de jogo
	JMP	sair_teclado 			; Saimos da rotina  

nao_ha_tecla:
	MOV	R1, MENOS_UM			; Guardamos -1 se nenhuma tecla foi pressionada 

sair_teclado:                                   ; Etiqueta para encerrar a rotina teclado 
	POP 	R7
	POP 	R6
	POP 	R5
	POP 	R4
	POP 	R3
	POP 	R2
	RET		      

; **********************************************************************
; * Rotina: processa_tecla
; * Esta rotina e responsavel por invocar a rotina correspondente a tecla
; * pressionada e em colocar em memoria a proxima posicao do pacman.
; * Argumentos:
; *	R1 --> Tecla pressionada em formato de jogo, 0H a FH
; *	R9 --> Tecla pressionada em formato de leitura do teclado	 
; * Saida:
; * 	{}
; **********************************************************************

processa_tecla:
	PUSH	R1
	PUSH	R2
	PUSH	R3
	PUSH	R4
	PUSH	R5
	
	MOV 	R5, ZERO			; Valor 0 no registo 
	MOV 	[UP_DOWN], R5			; Partimos do principio que o pacman nao anda
	MOV 	[RIGHT_LEFT], R5		; Partimos do principio que o pacman nao anda
	CMP	R1, MENOS_UM			; Verificamos se a tecla foi pressionada
	JZ	fim				; Se sim, náo se faz nada 
	MOV	R2, TRES			; Guardamos o valor da tecla 3
	CMP	R1, R2				; Vemos se e tecla 3
	JZ	tecla_tres			; Saltamos para a instrução de 3
	MOV	R2, CINCO			; Guardamos o valor da tecla 5
	CMP	R1, R2				; Vemos se e tecla 5
	JZ	tecla_cinco			; Saltamos para a instrução de 5
	MOV	R2, SETE			; Guardamos o valor da tecla 7
	CMP	R1, R2				; Vemos se e tecla 7	
	JZ	tecla_sete			; Saltamos para a instrução de 7
	MOV	R2, B_H				; Guardamos o valor da tecla B
	CMP	R1, R2				; Vemos se e tecla B
	JZ	tecla_B				; Saltamos para a instrução de B
	MOV	R2, C_H				; Guardamos o valor da tecla C
	CMP	R1, R2				; Vemos se e tecla C
	JZ	tecla_C				; Saltamos para a instrução de C
	MOV	R2, D_H				; Guardamos o valor da tecla D
	CMP	R1, R2				; Vemos se e tecla D
	JZ	tecla_D				; Saltamos para a instrução de D
	MOV	R2, E_H				; Guardamos o valor da tecla E
	CMP	R1, R2				; Verificamos se a tecla pressionada foi a letra E
	JZ	tecla_E				; Saltamos no código para executar o comando da letra E
	MOV	R2, F_H				; Guardamos o valor da tecla F
	CMP	R1, R2				; Vemos se e tecla F
	JZ	tecla_F				; Saltamos para a instrução de F
	JMP	teclas_esq			; Verificamos se anda para esquerda
tecla_tres:
	JMP	fim

tecla_cinco:
	JMP	fim

tecla_sete:
	JMP	fim

tecla_B:
	CALL 	tecla_som
	JMP	fim
	
tecla_C:
	;CALL	pausa_jogo
	JMP	fim
	
tecla_D:
	CALL	pausa_jogo			; Tecla responsavel por pausar o jogo
	JMP	fim

tecla_E:
	JMP	fim
	
tecla_F:
	MOV	R10, UM
	JMP	fim

teclas_esq:
	MOV	R3, R9				; Guardamos a tecla em formato nao convertido
	MOV 	R5, MASC_DETETA_ESQ		; Guardamos a mascara
	AND	R3, R5				; Vemos se e a primeira coluna (esquerda)
	JZ	teclas_direita			; Vemos se e a terceira coluna (direita)
	MOV	R4, MENOS_UM			; Anda para a esquerda: -1
	MOV	[RIGHT_LEFT], R4		; Escrevemos na memoria que anda para a esquerda

teclas_direita:
	MOV 	R3, R9				; Guardamos a tecla em formato nao convertido
	MOV 	R5, MASC_DETETA_DIR		; Guardamos a mascara
	AND	R3, R5 				; Vemos se e a terceira coluna
	JZ	teclas_cima			; Vemos se e a primeira linha
	MOV	R4, UM				; Anda para a direita: 1
	MOV 	[RIGHT_LEFT], R4		; Escrevemos na memoria que anda para a direita

teclas_cima:
	MOV 	R3, R9				; Guardamos a tecla em formato nao convertido
	MOV 	R5, MASC_DETETA_CIMA		; Guardamos a mascara
	AND	R3, R5				; Vemos se e a primeira linha
	JZ	teclas_baixo			; Vemos se e a terceira linha
	MOV	R4, MENOS_UM 			; Anda para cima 
	MOV	[UP_DOWN], R4			; Escrevemos na memoria que anda para baixo

teclas_baixo:
	MOV	R3, R9				; Guardamos a tecla em formato nao convertido
	MOV	R5, MASC_DETETA_BAIXO		; Guardamos a mascara
	AND 	R3, R5				; Vemos se e a terceira linha
	JZ	fim				; Saimos da rotina se nao
	MOV	R4, UM				; Anda para baixo
	MOV	[UP_DOWN], R4			; Escrevemos na memoria que anda para baixo

fim:
	POP	R5
	POP	R4
	POP	R3
	POP	R2
	POP	R1
	RET


; **********************************************************************
; * Rotina: pausa jogo
; * Esta rotina e responsavel por efetuar a pausa do jogo quando se carrega
; * na tecla D.
; * O valor da tecla primida neste ciclo encontra-se em R1 
; * O valor da tecla primida no ciclo anterior encontra-se em R8
; * Argumentos:
; *	--> R1 - Tecla primida em formato de jogo
; *	--> R8 - Tecla primida no loop anterior
; * Saida:
; * 	{}
; **********************************************************************

pausa_jogo:

; Guarda os registos na pilha e atualiza-os com os valores necessarios para o funcionamento da rotina

	PUSH	R2
	PUSH	R3
	PUSH	R4
	PUSH	R5
	PUSH	R8
	
	MOV	R4, DOIS			; Selecionamos o som de pausa
	MOV	R2, TRES 			; Guarda em R2 o cenario a reproduzir
	MOV	R3, D_H                         ; Guarda em R3 o valor 000DH
	CMP	R1, R8                          ; Verificamos se a tecla esta a ser primida continuamente
	JZ	pausa_fim			; Se sim, ignoramos 
	MOV	[SELECT_SOUND], R4		; Selecionamos o som

mete_pausa:
	DI 					; Desativamos as interrupcoes
	MOV	R5, TRES			; Seleciona som de fundo
	MOV	[STOP_GIVEN_SOUND], R5		; Pausa som
	MOV	R8, R1				; Registamos a tecla pressionada 
	MOV	[SELECT_FG_IMAGE], R2           ; Comando para meter um cenário de fundo
	CALL	teclado				; Clicamos outra vez para tirar a pausa?
	CMP	R1, R8                          ; Verificamos se a tecla esta a ser primida continuamente
	JZ	mete_pausa			; Se sim, ignoramos 
	CMP	R1, R3				; Se a tecla primida n e para tirar a pausa, continuamos a espera
	JNZ	mete_pausa			; Continua pausa se n pressionou D
	MOV	R2, ZERO			; Apaga cenário frontal
	MOV	[APAGAR_FG_IMAGE], R2		; voltamos ao jogo e ativamos as interrupcoes
	MOV	R1, TRES			; Seleciona som de fundo
	MOV 	[START_GIVEN_SOUND], R1			; Reproduz som de fundo
	EI					; Ativamos as interrupcoes

pausa_fim:
	POP	R8
	POP	R5
	POP	R4
	POP	R3
	POP	R2
	RET					; Retorna da função

; **********************************************************************
; * Rotina: tecla_som 
; * Esta rotina e responsavel por efetuar um efeito sonoro quando se carrega
; * na tecla B.
; * O valor da tecla primida neste ciclo encontra-se em R1 
; * O valor da tecla primida no ciclo anterior encontra-se em R8
; **********************************************************************

tecla_som:

; Guarda os registos na pilha e atualiza-os com os valores necessarios para o funcionamento da rotina

	PUSH	R2
	PUSH	R3

	MOV	R2, UM                          ; Guarda em R2 o som a reproduzir
	MOV	R3, B_H                         ; Guarda em R3 o valor 000BH
	CMP	R1, R8                          ; Verificamos se a tecla esta a ser primida continuamente
	JZ	tecla_som_fim                   ; Se sim, ignoramos 
	CMP	R1, R3                          ; Verifica se a tecla pressionada e a que queremos
	JZ	emite_som                       ; Se sim, emitimos som
	JMP	tecla_som_fim

emite_som:
	MOV	[SELECT_SOUND], R2              ; Comando para emitir o som selecionado

tecla_som_fim:

; Repõe o valor que estavam nos registos antes de ser usado na função

	POP	R3
	POP	R2
	RET                                          ; Retorna da função

; **********************************************************************
; * Rotina: converte_tecla 
; * Esta rotina e responsavel por converter o valor da tecla 
; * pressionada na sua representacao de jogo em hexadecimal (0H a FH)
; * O valor em binario/ hexadecimal encontra-se no registo R1
; * O valor de jogo e retornado em R1
; * Argumentos:
; *	--> R1 - Valor da tecla pressionada em formato de leitura
; * Saida:
;	--> R1 - Valor da tecla pressionada em formato de jogo
; **********************************************************************

converte_tecla:

   ; Guarda os registos na pilha e atualiza-os com os valores necessarios para o funcionamento da rotina

	PUSH	R2
	PUSH	R3
	PUSH	R4
	PUSH	R5

	MOV	R4, MASCARA                     ; Mascara para isolar a coluna
	MOV	R2, R1                          ; Copiamos o numero para outro registo
	SHR	R2, QUATRO                      ; Isolamos os 4 bits da linha no nibble low
	MOV	R5, MENOS_UM                    ; Variavel auxiliar

ver_linha:
	ADD	R5, UM                          ; R5 vai guardando o numero da linha 
	SHR	R2, UM                          ; Fazemos shift para ver onde esta a o bit a 1
	CMP	R2, ZERO                        ; Vemos se o bit do SHR era o 1 
	JNZ	ver_linha                       ; Se for zero quer dizer que encontramos a linha
	MOV	R2, R5                          ; Armazenamos a linha no registo R2
	MOV	R5, MENOS_UM                    ; Reiniciamos a variavel auxiliar R5 para ler a coluna
	MOV	R3, R1                          ; Guardamos o valor de R1 noutro registo
	AND	R3, R4                          ; Isolamos o nibble low

ver_coluna:

	ADD	R5, UM                          ; R5 vai guardando o numero coluna 
	SHR	R3, UM                          ; Fazemos shift para ver onde esta a o bit a 1
	CMP	R3, ZERO                        ; Vemos se o bit do SHR era o da coluna
	JNZ	ver_coluna                      ; Se for zero quer dizer que encontramos a coluna 
	MOV	R3, R5                          ; Armazenamos o valor da coluna no registo R3  
	SHL	R2, DOIS                        ; Multiplicamos o valor da linha por 4
	ADD	R2, R3                          ; Adicionamos a coluna ao valor anterior
	MOV	R1, R2                          ; Armazenamos no registo R1 o valor da tecla

; Repõe o valor que estavam nos registos antes de ser usado na função

	POP	R5
	POP	R4
	POP 	R3
	POP	R2
	RET                                    ; Retorna da rotina 

; *************************************************************************
; * Rotina: apaga_objeto
; * Esta rotina recebe o endereco de um objeto em R3, e os numeros da 
; * linha e coluna para comecar a apagar o objeto em R1 e R2 respetivamente
; * (tomando como referencia o pixel superior esquerdo do objeto). 
; *************************************************************************

apaga_objeto:
; Guarda os registos na pilha e atualiza-os com os valores necessarios para o funcionamento da rotina

	PUSH	R9
	MOV	R9, UM					; Ativa a funcionalidade de apagar
	JMP	obtem_dimensoes_objeto	; Salta para o bloco de codigo que efetivamente apaga o objeto e retorna da rotina


; **********************************************************************
; * Rotina: desenha_objeto
; * Esta rotina recebe o endereco de um objeto em R3, e os numeros da 
; * linha e coluna para comecar a desenhar o objeto em R1 e R2 respetivamente
; * (tomando como referencia o pixel superior esquerdo do objeto).
; **********************************************************************
desenha_objeto:
; Guarda os registos na pilha e atualiza-os com os valores necessarios para o funcionamento da rotina

	PUSH 	R9

	MOV	R9, ZERO			; desativa a funcionalidade de apagar

obtem_dimensoes_objeto:
	PUSH	R1
	PUSH	R2
	PUSH	R3
	PUSH 	R4
	PUSH 	R5
	PUSH	R6
	PUSH	R7
	PUSH	R8
	MOV	R4, [R3]                        ; Obtem a altura do objeto
	ADD     R3, DOIS                        ; Endereco da tabela que guarda a largura (DOIS porque a altura e uma word)
	MOV     R5, [R3]                        ; Obtem a largura do objeto
	ADD     R3, DOIS                        ; Endereco da cor do primeiro pixel (DOIS porque a largura e uma word)
	MOV	R7, R4                          ; Copia a altura do objeto para R7
	MOV	R6, ZERO						; Guarda um pixel "invisivel" em R6

init_variaveis_inter:                           ; Inicializa as variaveis de iteracao para a proxima iteracao do loop externo

	MOV	R8, R5                          ; Copia a largura do objeto para R8

verifica_apagar:
	CMP	R9, UM				; Verifica se R9 = 1
	JNZ	obtem_cor_pixels		; Se for 1, obtem a cor, caso contrário, a cor é "invisivel"
	JMP	poe_pixels			; Entra no loop de desenhar pixels

obtem_cor_pixels:
	MOV	R6, [R3]			; Obtem a cor do proximo pixel do objeto
	ADD	R3, DOIS        	        ; Endereco da cor do pixel seguinte (2 porque cada cor de pixel e uma word)

poe_pixels:      		                ; Desenha os pixels do objeto
	MOV	[DEFINE_LINHA], R1	        ; Seleciona a linha
	MOV	[DEFINE_COLUNA], R2             ; Seleciona a coluna
	MOV	[DEFINE_PIXEL], R6	        ; Altera a cor do pixel na linha e coluna selecionadas
	ADD	R2, UM                          ; Proxima coluna
	SUB	R8, UM			        ; Menos uma coluna para tratar
	JNZ	verifica_apagar                 ; Continua ate percorrer toda a largura do objeto
	SUB	R2, R5                          ; Repõe o valor da coluna inicial em R2
	ADD	R1, UM                          ; Proxima linha
	SUB	R7, UM                          ; Menos uma linha para tratar
	JNZ	init_variaveis_inter            ; Continua ate percorrer toda a altura do objeto

; Repõe o valor que estavam nos registos antes de ser usado na função
 
	POP	R8
	POP	R7
	POP	R6                                      
	POP	R5                                      
	POP	R4
	POP	R3
	POP	R2                      
	POP	R1
	POP	R9
	RET                                         ; Retorna da função

; **********************************************************************
; * Rotina: movimento_pacman
; * Rotina que e responsavel pelo movimento do pacman.
; * Apenas realiza o movimento quando a interrupcao rot_int_3 escreve na 
; * memoria que se pode realizar a interrupcao
; * Le nos enderecos UP_DOWN e RIGHT_LEFT as direcoes para que se move
; *; * Argumentos:
; *	{}
; * Saida:
; *  	--> R1 - Posicao Y
; *	--> R2 - Posicao X
; *	--> R3 - Objeto a desenhar
; *	--> R5 - variacao Y
; * 	--> R6 - variacao X
 
; **********************************************************************

movimento_pacman:
	PUSH	R1
	PUSH	R2
	PUSH	R3
	PUSH	R4
	PUSH	R5
	PUSH	R6
	PUSH	R8
	PUSH	R9	

	MOV 	R5, [UP_DOWN]
	MOV 	R6, [RIGHT_LEFT]
	MOV 	R8, UM

;Verifica se a interrupcao permite andar
	MOV	R9, [movimenta_pacman]		; Vemos o que foi escrito na memoria 
	ADD	R9, ZERO			; Vemos se a interrupcao escreveu na memoria que se pode andar
	JZ	sair_rotina_movimento_pacman 	; Saimos se n for para andar

	MOV	R3, DEF_PACMAN_RIGHT				; Obtem a tabela do objeto pacman
	MOV	R4, posicao_pacman					; Obtem a tabela da posicao do pacman
	MOV	R1, [R4]						; Guarda a linha da posicao do pacman
	ADD	R4, DOIS						; Obtem o endereco da coluna da posicao do pacman (2 porque uma WORD  sao 2 bytes)
	MOV	R2, [R4]						; Guarda a coluna da posicao do pacman

; Verifica se existe movimento, ou seja, se alguma tecla foi pressionada
	AND	R8, R5
	OR	R8, R6
	JZ	sair_rotina_movimento_pacman		; Se nao existe movimento (ambos R5 e R6 sao 0), sai imediatamente da rotina

;Verifica se o pacman sai das bordas do mapa (usa um "portal)

	MOV		R8, PRIMEIRA_LINHA-ALTURA_PAC	; Vemos se o pacman sai do ecra pela borda de cima
	CMP		R1,	R8							; Verificamos se a posicao da linha do pacman e igual a ele ter saido do ecra por cima
	JZ		portal_em_cima					; Se sim, damos jump

;Usamos o valor 1, pois o pixel de referencia da posicao encontra-se no canto superior esquerdo dos objetos
	MOV		R8, ULTIMA_LINHA+UM		; Vemos se o pacman sai do ecra pela borda de baixo
	CMP		R1,	R8							; Verificamos se a posicao da linha do pacman e igual a ele ter saido do ecra por baixo
	JZ		portal_em_baixo					; Se sim, damos jump

portais_horizontais:
	MOV		R8, PRIMEIRA_COLUNA-LARGURA_PAC	; Vemos se o pacman sai do ecra pela borda de cima
	CMP		R2,	R8							; Verificamos se a posicao da coluna do pacman e igual a ele ter saido do ecra por cima
	JZ		portal_esquerda					; Se sim, damos jump

;Usamos o valor 1, pois o pixel de referencia da posicao encontra-se no canto superior esquerdo dos objetos
	MOV		R8, ULTIMA_COLUNA+UM			; Vemos se o pacman sai do ecra pela borda de baixo
	CMP		R2,	R8							; Verificamos se a posicao da coluna do pacman e igual a ele ter saido do ecra por baixo
	JZ		portal_direita					; Se sim, damos jump

	JMP		portais_verificados				; Se o pacman nao saiu do ecra em nenhum sentido, saltamos diretamente para o movimento dele

portal_em_cima:
	MOV		R1,	ULTIMA_LINHA				; Guardamos em R1 a ultima linha do ecra (so vai aparecer a linha de cima do pacman)
	JMP		portais_horizontais				; Podemos entao verificar os portais horizontais (esquerda ou direita)

portal_em_baixo:
	MOV		R1,	PRIMEIRA_LINHA-ALTURA_PAC	; Guardamos em R1 a linha em que o pacman sai por completo do ecra
	ADD		R1,	UM							; Somamos 1, de modo a fazer aparecer a linha do fundo do pacman
	JMP		portais_horizontais				; Podemos entao verificar os portais horizontais (esquerda ou direita)

portal_esquerda:
	MOV		R2,	ULTIMA_COLUNA				; Guardamos em R2 a ultima coluna do ecra (so vai aparecer a coluna da esquerda do pacman)
	JMP		portais_verificados				; Dado que ja verificamos os portais verticais (cima ou baixo), podemos saltar para o movimento do pacman

portal_direita:
	MOV		R2,	PRIMEIRA_LINHA-ALTURA_PAC	; Guardamos em R2 a coluna em que o pacman sai por completo do ecra
	ADD		R2,	UM							; Somamos 1, de modo a fazer aparecer a coluna da direita do pacman
	JMP		portais_verificados				; Dado que ja verificamos os portais verticais (cima ou baixo), podemos saltar para o movimento do pacman

portais_verificados:

; Realiza o movimento
; R5 - Variacao Y
; R6 - Variacao X
; R1 - Posicao Y
; R2 - Posicao X
	CALL	movimento_objeto			; Chama rotina de movimento

; Da reset na memoria de movimento do pacman 
	MOV	R8, ZERO			; Guarda  0
	MOV	[UP_DOWN], R8			; Reset no andar vertical
	MOV	[RIGHT_LEFT], R8		; Reset no andar horizontal
 	MOV	[movimenta_pacman], R8


sair_rotina_movimento_pacman:
	POP	R9
	POP	R8
	POP	R6
	POP	R5
	POP	R4
	POP	R3
	POP	R2
	POP	R1
	RET					; Sai da rotina movimento_pacman


; *****************************************************************************
; * Rotina: movimenta_objeto
; * Responsavel por mover qualquer objeto
; * Argumentos:
; * 	--> R1 - Posicao Y
; *	--> R2 - Posicao X
; *	--> R3 - Objeto a desenhar
; *	--> R4 - Posicao na memoria do pacman 
; *	--> R5 - variacao Y
; * 	--> R6 - variacao X
; * Saida: 
; *	{}
; *****************************************************************************

movimento_objeto:
	PUSH	R5
	PUSH	R6
	PUSH	R8
	PUSH	R9
	PUSH	R1
	PUSH	R2
	PUSH	R3
	PUSH	R4

	CALL	apaga_objeto					; Apaga o pacman da sua posicao corrente (R1, R2, R3 sao parametros deste rotina)

; Inicializa os parametros da rotina desenha_objeto para desenhar o pacman e atualiza a posicao corrente do pacman

	CMP	R6, ZERO					; Verifica se o vetor horizontal e zero
	JNZ	verifica_colisao_h				; Se o vetor nao for zero, verificamos se existe uma colisao horizontal

sem_colisao_horizontal:						; Se nao houver colisao horizontal, somamos o vetor horizontal a coluna da posicao do objeto
	ADD	R2, R6						; Atualiza a posicao do pacman com o vetor de movimento horizontal
	MOV	[R4], R2					; Guarda a nova coluna da posicao do objeto em memoria

colisao_horizontal:						; Se houver uma colisao horizontal, a coluna da posicao do objeto fica inalterada
	SUB	R4, DOIS					; Obtem o endereco da linha da posicao do pacman (2 porque uma WORD  sao 2 bytes)
	CMP	R5, ZERO					; Verifica se o vetor vertical e zero
	JNZ	verifica_colisao_v				; Se nao for zero, verificamos se existe uma colisao nesse sentido

sem_colisao_vertical:						; Se nao houver colisao vertical, somamos o vetor vertical a linha da posicao do objeto
	ADD	R1, R5						; Atualiza a posicao do pacman com o vetor de movimento vertical
	MOV	[R4], R1					; Inicializa o parametro linha para a rotina desenha_objeto

colisao_vertical:						; Etiqueta que permite saltar a inicializacao dos parametros da rotinna desenha_objeto

	CALL desenha_objeto 					; Chama a rotina desenha_objeto

sair_rotina_movimento:
	POP	R4
	POP	R3
	POP	R2
	POP	R1
	POP	R9
	POP	R8
	POP	R6
	POP	R5
	RET					; Sai da rotina movimento_pacman

verifica_colisao_h:
; R3 - tabela do objeto em questao
; R1 - linha da posicao do objeto
; R2 - coluna da posicao do objeto
; R6 - vetor horizontal (diferente de zero)

	PUSH 	R1
	PUSH 	R2
	PUSH 	R3
	PUSH 	R4
	PUSH 	R5
	PUSH 	R6

	MOV 	R4, [R3]				; Guarda a altura do objeto em R4
	ADD	R3, DOIS			; Obtem o endereco da largura do objeto
	MOV 	R5, [R3]				; Guarda a largura do objeto em R5

	CMP 	R6, UM
	JNZ 	vetor_esquerda			; Se R6 for diferente de 1, entao processamos o vetor para a esquerda (R6 = -1)
	ADD 	R5, R2				; Obtemos a coluna a verificar (coluna imediatamente a direita do objeto)
	MOV 	[DEFINE_COLUNA], R5			; Defenimos a coluna a ser verificada no media center
	MOV		R6, MASCARA_FIRST_BIT		; Guarda a mascara que verifica se o ultimo bit da cor do pixel e 1
	JMP 	loop_vetores_horizontais		; Salta o loop de verificacao da coluna em questao

vetor_esquerda:
	MOV 	R5, R2				; Obtemos uma copia da coluna da posicao atual do objeto
	SUB 	R5, UM				; Obtemos a coluna a verificar (coluna imediatamente a esquerda do objeto)
	MOV 	[DEFINE_COLUNA], R5			; Defenimos a coluna a ser verificada no media center
	MOV		R6, MASCARA_FIRST_BIT		; Guarda a mascara que verifica se o ultimo bit da cor do pixel e 1

loop_vetores_horizontais:
	MOV 	[DEFINE_LINHA], R1			; Comecamos a verificar os pixels a partir da linha do pixel de referencia do objeto (canto superior esquedo)
	MOV 	R3, [OBTEM_PIXEL]			; Obtem o valor do pixel na posicao defenida
	AND		R3,	R6
	CMP 	R3, COR_PSEUDO_INVISIVEL		; Verificamos se o pixel em questao e um pixel de bloqueio
	JZ 	aux_colisao_horizontal
	ADD	R1, UM				; Obtemos a linha seguinte
	SUB	R4, UM				; Menos uma linha a verificar
	JNZ 	loop_vetores_horizontais		; Enquanto houver linhas a verificar, iteramos no loop

; Se nao existir colisao horizontal, repomos os valores dos registos, e voltamos para a rotina movimenta_objeto
	POP 	R6
	POP 	R5
	POP 	R4
	POP 	R3
	POP 	R2
	POP 	R1
	JMP 	sem_colisao_horizontal

aux_colisao_horizontal:				; Etiqueta que permite saltar a para o processamento de dados no caso de haver uma colisao horizontal
	POP 	R6
	POP 	R5
	POP 	R4
	POP 	R3
	POP 	R2
	POP 	R1
	JMP 	colisao_horizontal


verifica_colisao_v:
; R3 . tabela do objeto em questao
; R1 - linha da posicao do objeto
; R2 - coluna da posicao futura do objeto (depois de somada com o vetor horizontal)
; R5 - vetor vertical (diferente de zero)
	PUSH 	R1
	PUSH 	R2
	PUSH 	R3
	PUSH 	R4
	PUSH 	R5
	PUSH	R6

	MOV 	R4, [R3]				; Guarda a altura do objeto em R4
	ADD	R3, DOIS				; Obtem o endereco da largura do objeto
	MOV 	R6, [R3]				; Guarda a largura do objeto em R6

	CMP	R5, UM
	JNZ	vetor_cima			; Se R5 for diferente de 1, entao processamos o vetor para cima (R5 = -1)
	ADD 	R4, R1				; Obtemos a linha a verificar (linha imediatamente a direita do objeto)
	MOV 	[DEFINE_LINHA], R4		; Defenimos a linha a ser verificada no media center
	MOV		R5, MASCARA_FIRST_BIT	; Guarda a mascara que verifica se o ultimo bit da cor do pixel e 1
	JMP 	loop_vetores_verticais		; Salta o loop de verificacao da linha em questao

vetor_cima:
	MOV 	R4, R1				; Obtemos uma copia da linha da posicao atual do objeto
	SUB 	R4, UM				; Obtemos a linha a verificar (linha imediatamente acima do objeto)
	MOV 	[DEFINE_LINHA], R4		; Defenimos a linha a ser verificada no media center
	MOV		R5, MASCARA_FIRST_BIT	; Guarda a mascara que verifica se o ultimo bit da cor do pixel e 1

loop_vetores_verticais:
	MOV 	[DEFINE_COLUNA], R2		; Comecamos a verificar os pixels a partir da coluna do pixel de referencia do objeto (canto superior esquedo)
	MOV 	R3, [OBTEM_PIXEL]		; Obtem o valor do pixel na posicao defenida
	AND		R3,	R5					; Aplica a mascara para verificar se a ultima cor do pixel e 1
	CMP 	R3, COR_PSEUDO_INVISIVEL	; Verificamos se o pixel em questao e um pixel de bloqueio
	JZ 	aux_colisao_vertical
	ADD	R2, UM				; Obtemos a coluna seguinte
	SUB	R6, UM				; Menos uma coluna a verificar
	JNZ 	loop_vetores_verticais		; Enquanto houver colunas a verificar, iteramos no loop

; Se nao existir colisao vertical, repomos os valores dos registos, e voltamos para a rotina movimenta_objeto
	POP 	R6
	POP 	R5
	POP	R4
	POP 	R3
	POP 	R2
	POP 	R1
	JMP 	sem_colisao_vertical


aux_colisao_vertical:				; Etiqueta que permite saltar a para o processamento de dados no caso de haver uma colisao vertical
	POP 	R6
	POP 	R5
	POP 	R4
	POP 	R3
	POP 	R2
	POP 	R1
	JMP 	colisao_vertical

; **********************************************************************
; * Rotina: mete_fantasmas
; * Esta rotina é responsável por meter os quatro fantasmas no jogo a 
; * perseguir o pacman
; * A rotina envia em R3 a posicao do fantasma a colocar no ecra
; * Argumentos:
; *	{}
; * Saida:
; *  	{}	
; **********************************************************************

mete_fantasmas:
	PUSH	R1
	PUSH	R2
	PUSH	R3
	PUSH	R4

;Verifica se a interrupcao permite andar
	MOV	R1, [movimenta_fantasmas]		; Vemos o que foi escrito na memoria 
	ADD	R1, ZERO				; Vemos se a interrupcao escreveu na memoria que se pode andar
	JZ	sai_mete_fantasmas			; Saimos se n for para andar

	MOV 	R1, posicao_fantasma			; Vemos quantos fantasmas existem
	MOV	R2, [R1]				; Registo auxiliar para sabermos o numero de fantasmas
	MOV	R3, ZERO				; Registo auxiliar para iterar o numero de fantasmas
	CMP	R2, R3
	JZ 	sai_mete_fantasmas
	ADD	R1, DOIS				; Posicao do fantasma do primeiro fantasma

atualiza_fantasmas:
	MOV	R4, R1					; Guardamos a posicao do fantasma
	CALL	movimento_fantasma			; Movimento fanstasma 

	ADD	R3, UM					; +1 iteracao
	ADD	R1, QUATRO				; Proximo fantasma
	CMP	R2, R3					; Sobram fantasmas?
	JNZ	atualiza_fantasmas			; Se sim desenhamo-los


sai_mete_fantasmas:
; Da reset na memoria de movimento do pacman 
	MOV	R2, ZERO				; Guarda  0
 	MOV	[movimenta_fantasmas], R2 		; Atualiza na memoriaque e para esperar pela proxima interrupcao

	POP 	R4
	POP	R3
	POP	R2
	POP	R1
	RET


; **********************************************************************
; * Rotina movimento_fantasma
; * Esta rotina e responsavel por realizar o movimento dos fantasmas
; * Calcula a partir da posicao do pacman a direcao na qual o
; * fantasma se tem de mover
; * Recebe em R4 a posicao do fantasma a mover
; * Argumentos:	
; * 	--> R4 - Posicao do fantasma a mover
; * Saida:
; * 	{}
; **********************************************************************

movimento_fantasma:
	PUSH	R1
	PUSH	R2
	PUSH	R3
	PUSH	R4
	PUSH	R5
	PUSH	R6
	PUSH	R8
	PUSH	R9	

	MOV	R9, R4				; Guardamos a posicao do fantasma
	MOV	R3, DEF_FANTASMA		; Obtem a tabela do objeto 
	MOV	R1, [R4]			; R1 - Guarda a linha da posicao do fantasma
	ADD	R4, DOIS			; Obtem o endereco da coluna da posicao do fantasma  (2 porque uma WORD  sao 2 bytes)
	MOV	R2, [R4]			; R2 - Guarda a coluna da posicao do fantasma

; Obtem posicao atual do pacman 
	MOV	R4, posicao_pacman 		; Obtem linha do pacman
	MOV 	R8, [R4]

calcula_vertical:
	CMP	R8, R1				; Comparamos as posicoes. (pacman - fantasma)
	JNN	anda_para_baixo			; Se  linha pacman > fantasma, fantasma anda para baixo (+1 linha)
	CMP	R8, R1				; Comparamos as posicoes. (pacman - fantasma)
	JZ	nao_anda_vertical		; Se zero anda na horizontal
	MOV	R5, MENOS_UM			; Caso contrario, fantasma anda para cima (-1 linha)
	JMP 	calcula_horizontal		; Calculamos na horizontal

nao_anda_vertical:	
	MOV	R5, ZERO			; Varia 0 pixels na vertical
	JMP	calcula_horizontal		; Averiguamos a variacao nas colunas

anda_para_baixo:
	MOV 	R5, UM				; Anda para baixo, varia -1 pixel
	JMP 	calcula_horizontal		; Averiguamos a variacao nas colunas

calcula_horizontal:
	ADD 	R4, DOIS			; Endereco da coluna do pacman
	MOV	R8, [R4]			; Guardamos a coluna do pacman
	CMP	R8, R2				; Compara posicao da coluna (pacman - fantasma)
	JNN	anda_para_direita		; Se pacman > fantasma, avança 1 pixel
	CMP	R8, R2				; Comapara de novo	
	JZ 	nao_anda_horizontal		; Se 0, nao varia horizontalmente
	MOV	R6, MENOS_UM			; Se chegou aqui, anda para a esquerda, avanca -1 pixel 
	JMP	anda_fantasma			; Realizamos efetivamente o andar do fantasma

anda_para_direita:
	MOV	R6, UM				; Avança +1 pixel
	JMP	anda_fantasma			; Realizamos efetivamente o andar do fantasma

nao_anda_horizontal: 
	MOV 	R6, ZERO			; Nao avanca nenhuma coluna
	JMP	anda_fantasma			; Realizamos efetivamente o andar do fantasma
 

anda_fantasma:
; Inicializa os parametros da rotina desenha_objeto para desenhar o fantasma na nova posicao
; R5 - variacao vertical (Y)
; R6 - variacao horizontal (X)
; R1 - posicao Y
; R2 - posicao X
; R4 - posicao na memoria do objeto
; R3 - Objeto
	MOV	R4, R9				; Endereco do fantasma
	ADD	R4, DOIS			; +2 porque e assim que a rotina movimenta_objeto funciona
	MOV	R3, DEF_FANTASMA		; Objeto fantasma
	CALL 	movimento_objeto		; Chama rotina
	SUB	R4, DOIS
	
	

sair_rotina_movimento_fantasma:
	POP	R9
	POP	R8
	POP	R6
	POP	R5
	POP	R4
	POP	R3
	POP	R2
	POP	R1
	RET					; Sai da rotina movimento_fantasma

; **********************************************************************
; * Rotina: verifica_colisao_objeto
; * Esta rotina e responsavel por detetar colisoes entre objetos. Ou seja,
; * colisoes entre o pacman e os fantasamas e colisoes entre o mesmo e os
; * rebuçados. Escreve na memoria que houve colisao.
; * Vai ver ser nao existe sobreposicao de pixeis.
; * Argumentos:
; *	--> R3 - Tabela do objeto
; *	--> R4 - Posicao do objeto
; * 	--> R11 - Cor que colide com
; * Saida:
; * 	{}
; **********************************************************************


verifica_colisao_objeto:
	PUSH	R1
	PUSH 	R2 
	PUSH 	R3
	PUSH 	R4
	PUSH 	R5
	PUSH 	R6
	PUSH	R7
	PUSH	R8
	PUSH	R9
	PUSH	R10

; Guardamos a posicao no ecra e os limites de altura e largura
	MOV	R1, [R3] 		; Guarda altura
	MOV	R2, [R3 + DOIS]		; Guarda largura
	MOV	R6, [R4]			; Posicao Y
	MOV	R5, [R4 + DOIS]			; Posicao X

	MOV	R7, MENOS_UM	; aux linha
	MOV	R8, ZERO	; aux coluna

; primeiro pixel de todos do objeto
	ADD	R3, DOIS		; endereco antes do pixel 1 do objeto
	SUB 	R6, UM			; Começamos imediatamente antes
	SUB	R5, UM			; Começamos imediatamente antes


inicia_iter_linha:
	ADD	R7, UM			; 
	CMP	R1, R7			; Vimos todas as linhas?
	JZ	sair_colisao_objeto	; Sair 
	MOV	R8, MENOS_UM 
	ADD	R6, UM			; Andamos + 1 linha
	MOV	R5, [R4 + DOIS]			; Posicao X
	SUB	R5, UM			; Começamos imediatamente antes


inicia_iter_coluna:
	ADD	R8, UM			; Proximo pixel na mesma linha
	CMP	R2, R8			; Verifica se vimos a altura toda
	JZ 	inicia_iter_linha	; Se sim, proxima linha

	ADD	R5, UM			; x inicial + deslocamento na linha
	MOV	[DEFINE_LINHA], R6	; Linha a ler
	MOV	[DEFINE_COLUNA], R5	; Coluna a ler
	MOV	R10, [OBTEM_PIXEL]	; Guardamos a cor nessa posicao

	ADD	R3, DOIS		; Proxima cor na tabela do objeto
	MOV	R9, [R3] 		; Comparamos essa cor com a que esta no ecra. Se for diferente, existe colisao
	CMP	R11, R10
	JZ	existe_colisao		; Colisao com a cor indicada

	
	JMP	inicia_iter_coluna	; Vemos o proximo pixel na mesma linha

existe_colisao:
	MOV 	R4, UM			; Apontamos que houve colisao
	MOV	R3, ha_colisao 		; Endereco que ve se houve colisao
	MOV	[R3], R4		; Escrevemos que houve colisao



sair_colisao_objeto:
	POP	R10
	POP	R9	
	POP	R8
	POP	R7
	POP	R6
	POP 	R5
	POP	R4
	POP 	R3
	POP	R2
	POP 	R1
	RET




; **********************************************************************
; * Rotina: verifica_ derrota_
; * Esta rotina e responsavel por detetar colisoes entre os fantasmas e o
; * pacman. Guarda em R10 se houve ou nao colisao.
; * Argumentos:
; *	{}
; * Saida:
; * 	--> R10 - se ha derrota ou nao
; **********************************************************************

verifica_derrota:
	PUSH	R1
	PUSH	R2
	PUSH	R3
	PUSH	R11				; argumento para a rotina verifica_colisao_objeto

	MOV	R3, DEF_PACMAN_RIGHT		; Tabela com imagem pacman
	MOV	R4, posicao_pacman		; Posicao do pacman

; Manda R3 e R4 para a rotina verifica_colisao_objeto
	MOV	R11, COR_FANT			; Colisao com fantasma
	CALL 	verifica_colisao_objeto		; Chama rotina com R3 e R4

	MOV	R10, [ha_colisao]		; Vemos na memoria se houve colisao do pacman com fantasma
	
	POP	R11
	POP	R3
	POP	R2
	POP	R1
	RET


; **********************************************************************
; * Rotina: verifica_vitoria
; * Esta rotina e responsavel por detetar colisoes entre os rebucados e o
; * pacman. Guarda em R10 se houve vitoria ou nao.
; * Argumentos:
; *	{}
; * Saida:
; * 	--> R10 - se ha vitoria ou nao
; **********************************************************************

verifica_vitoria:
	PUSH	R1
	PUSH	R2
	PUSH	R3
	PUSH	R4
	PUSH	R5
	PUSH	R6
	PUSH 	R11

	MOV	R11, COR_PAC 		; Cor para detetar rebucado
	MOV	R1, rebucados_info	; Endereco com a informacao
	MOV	R2, [R1]		; Rebucados por apanhar
	ADD	R1, DOIS		; Endereco posicao do 1 rebucado
	MOV	R4, R1			; Guarda em R4  
	MOV	R3, DEF_REBUCADO	; Objeto rebucado
	MOV	R6, QUATRO		; 4x o loop

loop_rebucados:
	CMP	R6, UM				; Vimos stodos os rebucados?
	JN	sai_verifica_vitoria		; Ja vimos todos os rebucados

; Vemos se o pacman colido
; Manda R3 e R4 para a rotina verifica_colisao_objeto
	CALL 	verifica_colisao_objeto		; Chama rotina com R3 e R4
	MOV	R5, [ha_colisao]		; Vemos na memoria se houve colisao do pacman com rebucado 
	CMP	R5, UM				; Vemos se 1
	JZ	menos_um_rebucado		; Se 1 houve colisao. -1 rebucado
	ADD	R4, QUATRO			; Posicao proximo fantasma
	SUB	R6, UM				; +1 iter
	JMP 	loop_rebucados			; Repete loop

atualiza_vitoria:
	MOV	R1, [rebucados_info]		; Numero de rebucados
	CMP	R1, ZERO			; 0 rebucados?
	JNZ	sai_verifica_vitoria
	MOV 	R10, UM				; Metemos em R10 que se ganhou
	JMP	sai_verifica_vitoria		; Sai rotina

menos_um_rebucado:
	MOV	R5, [rebucados_info]		; Numero de rebucados
	SUB	R5, UM				; -1 rebucado
	MOV	[rebucados_info], R5			; Guarda resto
	MOV	R1,[R4]				; Linha	rebucado
	MOV	R2, [R4 + DOIS]			; Coluna rebucado	
	CALL	apaga_objeto			; Apaga Objeto
	MOV	R2, UM
	MOV	[SELECT_SOUND], R2              ; Comando para emitir o som selecionado
	MOV	R6, MENOS_DEZ			; Posicao fora do mapa
	MOV 	[R4], R6		; Rebucado sai do mapa
	MOV	[R4 + DOIS], R6		; Rebucado sai do mapa
	MOV 	R1, [posicao_pacman]		; Emenda pacman apagado
	MOV	R2, [posicao_pacman + DOIS]	; Emenda pacman apagado
	MOV	R3, DEF_PACMAN_RIGHT		; Emenda pacman apagado
	CALL	desenha_objeto			; Emenda pacman apagado
	MOV	R2, ZERO			; Limpa colisao
	MOV	[ha_colisao], R2		; Reset na colisao
	CMP	R5, ZERO			; 0?
	JZ 	atualiza_vitoria		; Metes em R10 que se ganhou

sai_verifica_vitoria:
	POP	R11
	POP	R6
	POP	R5
	POP	R4
	POP	R3
	POP	R2
	POP	R1
	RET


; **********************************************************************
; * Rotina: gera_tempo_fantasmas 
; * Esta rotina e responsavel por gerar os intervalos de tempo com que os
; * fantasmas dao spawn.
; * E gerado um numero aleatorio de 1 a 5, e adicionamos um fantasma quando
; * o resto da divisao do tempo decorrido por esse numero e 0. A rotina e 
; * chamada na rotina contador. Se o numero de fantasmas for superior a 4 
; * nao se faz nada.
; * Argumentos:
; *	{}
; * Saida:
; * 	{}
; **********************************************************************
gera_tempo_fantasmas:
	PUSH	R1
	PUSH	R2
	PUSH	R3

; Vemos quantos fantasmas estao em jogo
	MOV	R1, [posicao_fantasma]			; Numero de fantasmas
	CMP	R1, QUATRO				; Vemos se sao 4 ou +
	JNN	sai_gera_fantasmas			; Saimos caso sejam 4 ou +
	
; Gera numero aleatorio
gera_numero_aleatorio:
; Gera numero entre 1 - 5 
	MOV 	R1, [TEC_COL]				; Endereco PIN
	SHR	R1, QUATRO				; Isolamos os bits no ar
	MOV	R2, MASCARA				; 0000 1111
	AND	R1, R2					; Limpamos bits 7-4
	SHR	R1, DOIS				; Isolamos os 2 ultimso bits
	AND	R1, R2					; Limpamos bits 7-2
	ADD 	R1, SIX  				; Intervalo de 0-4 para 3-7 

; Vemos se o tempo decorrido é divisivel pelo numero aleatorio
	MOV 	R3, [CONTADOR]				; Vemos que valor esta no contador
	MOD	R3, R1					; Resto da divisao pelo numero aleatorio
	JNZ	sai_gera_fantasmas			; Sai da rotina

cria_novo_fantasma:
	MOV	R3, posicao_fantasma			; Numero de fantasmas
	MOV	R1, [R3]				; Guardamos em R1
	ADD	R1, UM					; +1 fantasma
	MOV	[R3], R1				; Atualizamos
	
sai_gera_fantasmas:
	POP	R3
	POP	R2
	POP	R1
	RET

; **********************************************************************
; * Este Bloco de codigo   responsavel por aumentar o valor do contador,
; * e fazer a conversao do mesmo de hex para decimal
; * o valor do contador vai de 0 ate 999
; * O valor do contador esta contido no  endereco CONTADOR
; * Quando o contador e incrementado, chama a rotina gera_tempo_fantasmas
; **********************************************************************
Aumenta_Contador:
; De segundo em segundo chamamos a rotina para adicionar fantasma.
; Se de facto e adicionado ou nao e aleatorio
	CALL 	gera_tempo_fantasmas

	MOV 	R3 , Mascara_nibble_L                   ; Mascara que isola os 4 bits menos significativos
	AND	R3, R10					; isola  as unidades do valor do contador
	MOV	R2, Mascara_nibble_L					
	MOV	R1, NOVE
	AND	R2, R1					; Coloca o valor 9 em R2
	CMP	R3, R2                          	; impede que o valor do contador sai da representacao decimal , verifica se R3=R2=9
	JZ 	converte_U				; caso R3 seja 9 então temos de tratar o valor do contador para que não passe de 9->A
	JMP 	indiferente				; caso R3 seja menor que 9 então adicionamos um a R10
converte_U:						; Transforma as unidades do contador em 0 e soma um as dezenas
	ADD 	R10, CONVERSAO_UNIDADES
	JMP 	converter_dezenas

indiferente:
	ADD	 R10, UM                                ; somar 1  ao valor do contador
	JMP	 sair_contador
	
converter_dezenas:					; este bloco faz com que as dezenas do contador fiquem entre 0-9 ( tal com o das unidades)
	MOV     R3, Mascara_nibble_H			; isola o 2 nibble menos significativo	(Dezenas)
	MOV     R3, R10					; Colocar o valor das dezenas em R3
	SHR     R3, QUATRO				; Colocar o valor das dezenas no nibble menos significativo de R3
	MOV     R5, Mascara_nibble_L				     	 
	AND     R5, R3					; fazer uma copia de R3
	MOV     R1, letra_hexadecimal			; colocar o valor de 10 em hex para R1
	CMP     R5, R1					; Comparar se as dezenas do ccontador ,R5, passam do valor 9 (o valor em questão é 000AH)
	JZ      converte_D				; converter as dezenas para 0 e somar um as centenas
	JMP     sair_contador

converte_D:
	ADD     R3, SIX					; R3 = A + 6 = 0, vai um
	SHL     R3, QUATRO				; multiplica R3 por 16 
	MOV     R2, Mascara_nibble_L					
	AND     R10, R2					; isola o valor das unidades em R10
	OR      R10, R3					; junta o valor das centas dezenas e unidades em R10

	SHR     R3, OITO								
	CMP     R3, R1									
	JZ      contador_no_maximo
	JMP     sair_contador

contador:
	PUSH	R1                               ; Guardar os valores de R1,R2,R3,R4,R5,R10
	PUSH	R2
	PUSH	R3
	PUSH	R4
	PUSH    R5
	PUSH    R10	
	

	MOV 	R5, DISPLAYS			; Endereco do periferico dos displays
	MOV     R4, CONTADOR			; Endereco com valor do contador
	MOV 	R10, [R4]			; Transferir o valor do contador para R10
	MOV 	[R5], R10			; escreve contador nos displays
	MOV     R3, CONTAGEM			; vemos se a interrupcao permite contar
	MOV	R2, [R3]			; colocar o valor contido no endereco R3 em R2   
	CMP     R2, UM				;  COmparar se o valor de R2 e igual a 1
	JZ	Aumenta_Contador	        
	JMP     sair_contador            
	    
sair_contador:                            	; Guarda-se o valor de R10 no  endereco com o antigo valor do contador
	MOV     R3, ZERO
	MOV     [CONTAGEM], R3                  ; voltar o valor de contagem a zero 
	MOV     [R4], R10			; atualizar o valor do contador
contador_no_maximo:							  
	POP 	R10				; Recuperar  os valores de R1,R2,R3,R4,R5,R10
	POP 	R5
	POP	R4	
	POP	R3
	POP	R2
	POP	R1
	RET                                      ; sai da rotina contador


desenha_nivel:

; R1 - tabela que define os pixels de bloqueio do nivel em questao

	MOV 	R2, [R1]						; Obtem o total de pixels de bloqueio do nivel
	MOV 	R3, COR_PSEUDO_INVISIVEL	; Obtem a cor dos pixels de bloqueio

ciclo_desenha_nivel:
	ADD 	R1, DOIS					; Obtem a linha do pixel (somamos dois porque uma WORD sao 2 bytes)
	MOV	R4, [R1]					; Guardamos essa linha em R4
	MOV 	[DEFINE_LINHA], R4			; Define a linha desse pixel no media center
	ADD 	R1, DOIS					; Obtem a coluna do pixel (somamos dois porque uma WORD sao 2 bytes)
	MOV	R4, [R1]					; Guardamos essa coluna em R4
	MOV 	[DEFINE_COLUNA], R4			; Define a coluna desse pixel no media center
	MOV 	[DEFINE_PIXEL], R3	; Desenha um pixel de bloqueio na posicao defenida
	SUB 	R2, UM						; Menos um pixel para processar
	JNZ 	ciclo_desenha_nivel
	RET




