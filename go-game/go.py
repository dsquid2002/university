#TAD intersecao
#Construtor
def cria_intersecao(letra, num):
    """Cria uma interseção

    Recebe um caracter e um inteiro correspondente à coluna col e à linha lin e
    devolve a interseção correspondente.
    
    cria_intersecao: {letra,num} --> intersecao
    """
    inter = (letra, num)
    if not eh_intersecao(inter):
        raise ValueError('cria_intersecao: argumentos invalidos')
    return inter

#Seletores
def obtem_col(inter):
    """Obtém coluna

    Devolve a coluna da interseção inter.
    
    obtem_col: intersecao --> str
    """
    return inter[0]

def obtem_lin(inter):
    """Obtém linha

    Devolve a linha da interseção inter.
    
    obtem_lin: intersecao --> int
    """
    return inter[1]

#Reconhecedor
def eh_intersecao(arg):
    """Verifica se  é interseção

    Recebe um argumento e verifica se é uma interseção.
    
    eh_intersecao: universal --> booleano
    """
    return not (not isinstance(arg, tuple) or len(arg) != 2 or \
    type(arg[0]) != str or len(arg[0]) != 1 or type(arg[1]) != int or \
    ord(arg[0]) not in range(65,84) or arg[1] not in range(1,20))

#Teste
def intersecoes_iguais(arg1, arg2):
    """Interseções iguais

    Verfica se as interseções inseridas são iguais.
    
    intersecoes_iguais: {universal,universal} --> booleano
    """
    return eh_intersecao(arg1) and eh_intersecao(arg2) and  \
        obtem_col(arg1) == obtem_col(arg2) and obtem_lin(arg1) == obtem_lin(arg2)

#Transformador
def intersecao_para_str(inter):
    """Devolve a string associada
    
    Recebe uma interseção e devolve uma string que representa o seu argumento.
    
    intersecao_para_str: intersecao --> str
    """
    return obtem_col((inter)) + str(obtem_lin(inter))

def str_para_intersecao(caract):
    """Devolve a interseção associada

    Recebe uma string e devolve uma interseção que representa o seu argumento.
    
    str_para_intersecao: str --> intersecao
    """
    return (caract[0],int(caract[1:]))
#-------------------------------------------------------------------------#
#Funções de alto nível
def obtem_intersecoes_adjacentes(inter, inter_superior):
    """Obtém as as interseções adjacentes de uma interseção

    A função devolve um tuplo com as interseções adjacentes à interseção inter
    de acordo com a ordem de leitura. inter_superior corresponde à interseção superior 
    direita do tabuleiro Go.
    
    obtem_intersecoes_adjacentes: {intersecao,intersecao} --> tuplo
    """
    adj_validas = ()
    #Tuplo que irá conter todas as interseções adjacentes válidas
    adj_dir = (chr(ord(obtem_col(inter)) + 1), obtem_lin(inter))
    adj_esq = (chr(ord(obtem_col(inter)) - 1), obtem_lin(inter))
    adj_cima = (obtem_col(inter), obtem_lin(inter)  + 1)
    adj_baixo = (obtem_col(inter), obtem_lin(inter) - 1)
    #Todas as possíveis interseções
    adj_todas = (adj_baixo, adj_esq, adj_dir, adj_cima)
    #Um tuplo com todas as interseções possíveis é criado para facilitar a validação
    letras = tuple(chr(i) for i in range(65, ord(obtem_col(inter_superior)) + 1))
    numeros = tuple(i for i in range(1, obtem_lin(inter_superior) + 1))
    for adj in adj_todas:
        try:
            inter = cria_intersecao(adj[0], adj[1])
            if obtem_col(inter) in letras and \
                obtem_lin(inter) in numeros:
                adj_validas += (adj,)
                #Se as coordenas tiverem dentro dos valores válidos do território fornecido, considera válido
        except ValueError:
            pass
    return adj_validas

def ordena_intersecoes(tup): 
    """Ordena interseções 
    
    A função devolve um tuplo de interseções com as mesmas interseções de tup ordenadas
    de acordo com a ordem de leitura do tabuleiro Go.
    
    ordena_intersecoes: tup --> tup
    """
    return tuple(sorted(tup, key = lambda x: (obtem_lin(x),obtem_col(x))))
#-------------------------------------------------------------------------#
#TAD pedra
#Construtores
def cria_pedra_branca():
    """Devolve pedra

    A função devolve uma pedra branca.
    
    cria_pedra_branca: {} --> pedra
    """
    return 1

def cria_pedra_preta():
    """Devolve pedra

    A funçãoo devolve uma pedra preta.
    
    cria_pedra_preta: {} --> pedra
    """
    return 2

def cria_pedra_neutra():
    """Devolve pedra

    A função devolve uma pedra neutra.
    
    cria_pedra_neutra: {} --> pedra
    """
    return 0 

#Reconhecedor
def eh_pedra(arg):
    """Verifica se é pedra

    A função devolve True caso a pedra arg seja um TAD pedra e False
    caso contrário.
    
    eh_pedra: universal --> booleano
    """
    return type(arg) == int and arg in range(3)

def eh_pedra_branca(p):
    """Verifica se é pedra branca

    A função devolve True caso a pedra p seja do jogador branco e Falso
    caso contrário.
    
    eh_pedra_branca: pedra --> booleano
    """
    return eh_pedra(p) and p == 1

def eh_pedra_preta(p):
    """Verifica se é pedra preta

    A função devolve True caso a pedra p seja do jogador preto e Falso
    caso contrário.
    
    eh_pedra_branca: pedra --> booleano
    """
    return eh_pedra(p) and p == 2

#Teste
def pedras_iguais(p1, p2):
    """Verifica se as pedras são iguais

    A função devolve True caso p1 e p2 sejam pedras e iguais.
    
    pedras_iguais: {universal,universal} --> booleano
    """
    return eh_pedra(p1) and eh_pedra(p2) and p1 == p2

#Transformador
def pedra_para_str(p):
    """Pedra para string
    
    A função devolve a cadeia de caracteres que representa o jogador dono da pedra,
    isto é 'O', 'X' ou '.', ou seja, pedras do jogador branco, preto ou neutras, respetivamente.
    
    pedra_para_str: pedra --> str
    """
    if p == 1:
        return 'O'
    elif p == 2:
        return 'X'
    else:
        return '.'
#-------------------------------------------------------------------------#
#Funções de alto nível
def eh_pedra_jogador(p):
    """Averigua se a pedra é de jogador

    função que recebe uma pedra e devolve True se a mesma disser respeito
    à pedra de um jogador e False, caso contrário.

    eh_pedra_jogador: pedra --> booleano
    """
    return eh_pedra_branca(p) or eh_pedra_preta(p)
#-------------------------------------------------------------------------#
#TAD goban
#Construtores#
def cria_goban_vazio(n): 
    """Cria um goban vazio

    A função devolve um goban de tamanho n x n, sem interseções ocupadas.
    
    cria_goban_vazio: int --> goban
    """
    if not isinstance(n, int):
        raise ValueError("cria_goban_vazio: argumento invalido")      
    goban = {v: {c: cria_pedra_neutra() for c in range(1,n+1)} for v in [chr(c) for c in range(65 , 65 + n)]}
    if not eh_goban(goban):    
        raise ValueError("cria_goban_vazio: argumento invalido")
    return goban

def cria_goban(n, inter_b, inter_p): 
    """Cria um goban

    A função devolve um goban de tamanho n x n, com as interseções do
    tuplo inter_b ocupadas por pedras brancas e as interseções do tuplo inter_p
    ocupadas por pedras pretas. A função levanta um ValueError caso os argumentos
    inseridos sejam inválidos.
    
    cria_goban: {int, tuplo,tuplo} --> goban
    """
    try:
        goban = cria_goban_vazio(n)
    except ValueError:
        raise ValueError("cria_goban: argumentos invalidos")
    #Certificamo-nos que o erro levantado é aquele que queremos
    def insere_ped(tup, f_cria_ped):
        try:
            for inter in tup:
                if not eh_intersecao(inter) or not eh_intersecao_valida(goban, inter):
                    raise ValueError("cria_goban: argumentos invalidos")
        except (TypeError,IndexError) as err:
            raise ValueError("cria_goban: argumentos invalidos")
        #Caso os argumentos nos supostos tuplos sejam incorretos
        for inter in tup:
            if not eh_pedra_jogador(obtem_pedra(goban, inter)):
                goban[obtem_col(inter)][obtem_lin(inter)] = f_cria_ped
                #Não podemos inserir duas pedras na mesma interseção
            else:
                raise ValueError("cria_goban: argumentos invalidos")
    insere_ped(inter_b, cria_pedra_branca())
    insere_ped(inter_p, cria_pedra_preta())
    return goban

def cria_copia_goban(goban):
    """Cria cópia do goban

    Recebe um goban e devolve a cópia do goban.
    
    cria_copia_goban: goban --> goban
    """
    goban_copia = cria_goban_vazio(obtem_lin(obtem_ultima_intersecao(goban)))
    for letra, dic_l in goban.items():
        for numero in dic_l:
            goban_copia[letra][numero] = goban[letra][numero]
        #Isto pois os dicionários funcionam por endereços de memória e não fazem cópias automaticamente
    return goban_copia

#Seletores
def obtem_ultima_intersecao(goban):
    """Obtém última interseção
    
    A função devolve a interseção correspondente ao canto superior
    direito.
    
    obtem_ultima_intersecao: goban --> intersecao
    """
    return (chr(64 + len(goban)),len(goban))

def obtem_pedra(goban, inter):
    """Obtém a pedra na interseção
    
    A função devolve a pedra na interseção inter do goban. Se a posição não
    estiver ocupada devolve uma pedra neutra.
    
    obtem_pedra: {goban,intersecao} --> pedra
    """
    #Verificamos que tipo de pedra está na interseção. Devolvemos essa pedra construída
    if eh_pedra_branca(goban[obtem_col(inter)][obtem_lin(inter)]):
        return cria_pedra_branca()
    elif eh_pedra_preta(goban[obtem_col(inter)][obtem_lin(inter)]):
        return cria_pedra_preta()
    else:
        return cria_pedra_neutra()

def obtem_cadeia(goban, inter):
    """Obtém cadeia 

    A função devolve o tuplo formado pelas interseções (em ordem de leitura)
    das pedras ou de interseções livres da cadeia que passa na interseção inter.
    
    obtem_cadeia: {goban,intersecao} --> tuplo
    """
    cadeia = (inter,)
    estado = True
    while estado:
        estado = False
        #Asssume que já não irá existir nenhuma adição ao tuplo da cadeia. Caso contrário, é metido a True
        for elemento in cadeia:
            adjacentes = obtem_intersecoes_adjacentes(elemento,obtem_ultima_intersecao(goban))
            #Obtém todas as interseções adjacentes de cada elemento no tuplo da cadeia
            for possivel_int in adjacentes:
                if pedras_iguais(obtem_pedra(goban,inter), obtem_pedra(goban,possivel_int)) and possivel_int not in cadeia:
                    #Verifica se a interseção adjacente é do tipo pretendido (preto, branco ou vazio) e se já pertence à cadeia
                    cadeia += (possivel_int,)
                    estado = True
                    #Indica se se adicionou mais algum elemento. Se sim, ainda podem existir mais elementos da cadeia por adicionar e repete-se o processo
    return ordena_intersecoes(cadeia)

#Modificadores
def coloca_pedra(goban, inter, ped):
    """Coloca pedra na interseção

    A função modifica destrutivamente o goban colocando a pedra ped
    na interseção inter, devolvendo o próprio goban.
    
    coloca_pedra: {goban,intersecao,pedra} --> goban
    """
    #Verificamos primeiro o tipo de pedra e depois alteramos. 
    def ver_ped():
        if eh_pedra_branca(ped):
            return cria_pedra_branca()
        elif eh_pedra_preta(ped):
            return cria_pedra_preta()  
    if not eh_pedra_jogador(obtem_pedra(goban, inter)):
        #Se a inter está vazia
        goban[obtem_col(inter)][obtem_lin(inter)] = ver_ped()        
    return goban

def remove_pedra(goban, inter):
    """Remove pedra

    Modifica destrutivamente o goban, removendo a pedra ped da interseção
    inter.
    
    remove_pedra: {goban,intersecao} --> goban
    """
    goban[obtem_col(inter)][obtem_lin(inter)] = cria_pedra_neutra()
    return goban

def remove_cadeia(goban, tup_cad):
    """Remove cadeia

    A função modifica destrutivamente o goban, removendo as pedras nas interseções
    do tuplo tup_cad.
    
    remove_cadeia: {goban, tuplo} --> goban
    """
    for pedra in tup_cad:
        remove_pedra(goban, pedra)
    return goban

#Reconhecedores
def eh_goban(arg): 
    """Verifica se é goban
    
    A função devolve True caso o argumento seja um TAD goban e False,
    caso contrário.
    
    eh_goban: universal --> booleano
    """
    if type(arg) != dict or (len(arg) not in (9,13,19)): 
        return False
    #Se o arg é um dicionário com as dimensões válidas  
    for coluna in arg.values():
        if type(coluna) != dict or len(arg) != len(coluna):
            return False
        for inteiro in coluna.values():
            if not eh_pedra(inteiro):
                return False
    #Se as listas são válidas, ou seja, as linhas
    return True

def eh_intersecao_valida(goban, inter):
    """Verifica se é interseção válida
    
    A função devolve True caso a interseção inter seja uma interseção
    válida no goban.
    
    eh_intersecao_valida: {goban,intersecao} --> booleano
    """
    if not eh_intersecao(inter) and not eh_goban(goban):
        return False
    letras = tuple(chr(i) for i in range(65, ord(obtem_col(obtem_ultima_intersecao(goban))) + 1))
    numeros = tuple(i for i in range(1, obtem_lin(obtem_ultima_intersecao(goban)) + 1))
    return obtem_col(inter) in letras and obtem_lin(inter) in numeros 

#Teste
def gobans_iguais(arg1, arg2):
    """Verifica se os goban são iguais
    
    Devolve True caso arg1 e arg2 forem gobans e forem iguais.
    
    goban_iguais: {universal,universal} --> booleano   
    """
    if not (eh_goban(arg1) and eh_goban(arg2) and \
        intersecoes_iguais(obtem_ultima_intersecao(arg1),obtem_ultima_intersecao(arg2))):
        return False
    letras = tuple(chr(i) for i in range(65, ord(obtem_col(obtem_ultima_intersecao(arg1))) + 1))
    numeros = tuple(i for i in range(1, obtem_lin(obtem_ultima_intersecao(arg1)) + 1))
    for letra in letras:
        for numero in numeros:
            if not (pedras_iguais(obtem_pedra(arg1, cria_intersecao(letra,numero)), obtem_pedra(arg2, cria_intersecao(letra,numero)))):
                    return False
    return True

#Transformador
def goban_para_str(goban):
    """Converte o goban numa cadeia de caracteres

    Devolve a cadeia de caracteres que representa o goban.
    
    goban_para_str: goban --> str
    """
    letras = tuple(chr(i) for i in range(65, ord(obtem_col(obtem_ultima_intersecao(goban))) + 1))
    numeros = tuple(range(1, obtem_lin(obtem_ultima_intersecao(goban)) + 1))
    def imprime_letras():
        linha_letras = '  '
        for col in letras:
            linha_letras = linha_letras + '{:>2}'.format(col)
        linha_letras += '\n'
        return linha_letras
    #Imprime a primeira linha com as letras identificadoras
    g_str = imprime_letras()
    for lin in numeros[::-1]:
        g_str = g_str + '{:>2}'.format(lin)    
        for col in goban.keys():
            if eh_pedra_branca(goban[col][lin]):
                g_str += '{:>2}'.format(pedra_para_str(cria_pedra_branca()))
            elif eh_pedra_preta(goban[col][lin]):
                g_str += '{:>2}'.format(pedra_para_str(cria_pedra_preta()))
            else:
                g_str += '{:>2}'.format(pedra_para_str(cria_pedra_neutra()))
        g_str = g_str + ' {:>2}\n'.format(lin)
    g_str += imprime_letras()[:-1] 
    #Não queremos o \n final
    return g_str
#-------------------------------------------------------------------------#
#Funções de alto nível
def obtem_territorios(goban):
    """Obtém os territórios de um tabuleiro goban
    
    Função que recebe um goban e que devolve o tuplo formado pelos
    tuplos com as interseções de cada território desse goban. O tuplo devolvido
    encontra-se ordenado de acordo com a ordem de leitura do tabuleiro de Go, 
    assim como as interseções.
    
    obtem_territorios: goban --> tuplo
    """
    def ordena(list_aux): 
        pivots = ()
        for inter in list_aux:
            pivots += (inter[0],)
        pivots_ordenados = ordena_intersecoes(pivots)
        terr_ordenados = ()
        for inter in pivots_ordenados:
            for terr in list_aux:
                if inter in terr:
                    terr_ordenados += (terr,)
        return terr_ordenados
    todas_inter_vazias,terr_seen = [], ()
    estado = True
    letras = tuple(chr(i) for i in range(65, ord(obtem_col(obtem_ultima_intersecao(goban))) + 1))
    numeros = tuple(range(1, obtem_lin(obtem_ultima_intersecao(goban)) + 1))
    for coluna in letras:
        for linha in numeros:
        #Iteramos cada interseção no tabuleiro começando em A1
            if cria_intersecao(coluna,linha) in terr_seen:  
                #Vemos se a interseção em questão já foi contabilizada
                estado = False
            else:
                estado = True
                cad = obtem_cadeia(goban,cria_intersecao(coluna,linha))
            if not eh_pedra_jogador(obtem_pedra(goban,cria_intersecao(coluna,linha))) and estado:
                todas_inter_vazias.append(cad)
                terr_seen += (cad)
    return ordena(todas_inter_vazias)

def obtem_adjacentes_diferentes(goban, tup):  
    """ Obtém as interseções adjacentes diferentes de um tuplo
    
    Função que recebe um tabuleiro goban e um tuplo de interseções ocupadas
    por pedras de jogador ou interseções livres e que devolve as liberdades
    ou a fronteira do mesmo, respetivamente.
    
    obtem_adjacentes_diferentes: {goban, tuplo} --> tuplo
    """  
    inter_ah_volta = []
    for intersecao in tup:
        #Iteramos cada interseção no tuplo fornecido
        possiveis_inter = obtem_intersecoes_adjacentes(intersecao, obtem_ultima_intersecao(goban))
        for uma_possivel_inter in possiveis_inter:
            if not pedras_iguais(obtem_pedra(goban, uma_possivel_inter) ,obtem_pedra(goban, tup[0]) ) and \
            uma_possivel_inter not in inter_ah_volta:
                #Verificamos se a interseção é diferente e se já não foi contada
                if eh_pedra_jogador(obtem_pedra(goban, tup[0])) and not eh_pedra_jogador(obtem_pedra(goban,uma_possivel_inter)):
                    inter_ah_volta += (uma_possivel_inter,) 
                elif not eh_pedra_jogador(obtem_pedra(goban, tup[0])) and eh_pedra_jogador(obtem_pedra(goban,uma_possivel_inter)):
                    inter_ah_volta += (uma_possivel_inter,) 
    return ordena_intersecoes(inter_ah_volta)
def jogada(goban, inter, ped): 
    """Realiza uma jogada

    Função que modifica destrutivamente o goban (goban) colocando a pedra
    de jogador (ped) na interseção (inter) e remove todas as pedras do jogador
    contrário pertecentes a cadeias adjacentes à interseção sem liberdades, 
    devolvendo o próprio goban.

    jogada: {goban,intersecao, pedra} --> goban
    """
    coloca_pedra(goban, inter, ped)
    inter_adjacentes = obtem_intersecoes_adjacentes(inter, obtem_ultima_intersecao(goban))
    #Apenas existe captura quando a pedra que colocamos corta a última liberdade de uma peça adjacenta à mesma
    for i_adj in inter_adjacentes:
        if eh_pedra_jogador(obtem_pedra(goban, i_adj)) and not pedras_iguais(obtem_pedra(goban, inter), obtem_pedra(goban, i_adj) ) and \
            len(obtem_adjacentes_diferentes(goban, obtem_cadeia(goban, i_adj))) == 0:
            #Vemos se a interseção adjacente é uma pedra do oponente e se esta fica sem liberdades
            remove_cadeia(goban, obtem_cadeia(goban,i_adj))
    return goban

def obtem_pedras_jogadores(goban):
    """Obtém as pedras de cada jogador

    Função que devolve um tuplo de dois inteiros que correspondem
    ao número de peças do jogador branco e preto, respetivamente.
    
    obtem_pedras_jogadores: goban --> tuplo
    """
    n_pretas = 0
    n_brancas = 0
    letras = tuple(chr(i) for i in range(65,ord(obtem_col(obtem_ultima_intersecao(goban))) +1))
    numeros = tuple(range(1, obtem_lin(obtem_ultima_intersecao(goban)) + 1))
    for coluna in letras:
        for linha in numeros:
            if eh_pedra_preta(obtem_pedra(goban,cria_intersecao(coluna,linha))):
                n_pretas += 1
            if eh_pedra_branca(obtem_pedra(goban, cria_intersecao(coluna,linha))):
                n_brancas += 1        
    return n_brancas,n_pretas
#-------------------------------------------------------------------------#
#Funções adicionais
def calcula_pontos(goban):
    """Calcula o número de pontos

        Função auxiliar que recebe um goban e devolve o tuplo de dois inteiros
        com as pontuações dos jogadores branco e preto, respetivamente.
        
        calcula_pontos: goban --> tuple
    """
    pontos_b = obtem_pedras_jogadores(goban)[0]
    pontos_p = obtem_pedras_jogadores(goban)[1]
    todos_territorios = obtem_territorios(goban)
    for territorio in todos_territorios:
        aux = True
        #Assumimos que a fronteira é sempre composts por pedras iguais
        if len(territorio) != (obtem_lin(obtem_ultima_intersecao(goban)))**2:
        #Discartamos o caso em que o tabuleiro está vazio, o território não é de ninguém
            fronteira = obtem_adjacentes_diferentes(goban, territorio)
            for intersecao in fronteira:
                aux = aux and pedras_iguais(obtem_pedra(goban, intersecao), obtem_pedra(goban, fronteira[0]))
                #Averiguamos se a fronteira de um território é composta apenas por pedras iguais
            if aux:
                #Se a fronteira só possui um tipo de pedra, averiguamos qual é que somamos os pontos
                if eh_pedra_branca(obtem_pedra(goban, fronteira[0])):
                    pontos_b += len(territorio)
                elif eh_pedra_preta(obtem_pedra(goban, fronteira[0])) :
                    pontos_p += len(territorio)
    return pontos_b,pontos_p

def eh_jogada_legal(goban,inter,ped,goban_proibido):
    """Determina se uma jogada é legal

        Função auxiliar que recebe um goban (goban), uma interseção (inter), uma
        pedra de jogador (ped) e um outro goban (goban_proibido) e devolve True
        se a jogada for legal, False caso contrário. goban_proibido diz respeito ao 
        estado do tabuleiro que não pode ser obtido.
        
        eh_jogada_legal: {goban,intersecao,pedra,goban} --> booleano
    """
    aux_goban = cria_copia_goban(goban)
    if not eh_pedra_jogador(obtem_pedra(goban, inter)):
        possivel_jog = jogada(aux_goban, inter, ped)
    else:
        return False
    #1.Verificamos se a jogada não é suícidio
    #2.Verificamos se não cometemos uma repetição e se não se colocam pedras por cima de pedras
    return not (len(obtem_adjacentes_diferentes(aux_goban, obtem_cadeia(possivel_jog, inter))) == 0 or\
        gobans_iguais(possivel_jog, goban_proibido))

def turno_jogador(goban,ped,goban_proibido):
    """Realiza turno de jogador

    Função auxiliar que recebe um goban (goban), uma pedra de jogador (ped),
    um outro  goban (goban_proibido), e oferece ao jogador que joga com pedras (ped)
    a opção de passar ou de colocar uma pedra própria numa interseção. Se o jogador 
    passar, a função devolve False sem modificar argumentos. Caso contrário, devolve
    True e modifica destrutivamente o tabuleiro goban de acordo com a joagada realizada.
    Até que o jogador passe ou introduza uma jogada correta, o jogo não avança.
    goban_proibido diz respeito ao estado em que o tabuleiro não pode ser obtido após
    a resolução da jogada.
    
    turno_jogador: {goban, pedra, goban} --> booleano
    """
    aux = True
    while aux:
        possivel_jog = str(input("Escreva uma intersecao ou 'P' para passar [{}]:".format(pedra_para_str(ped))))
        if possivel_jog == 'P':
            return False
        try:
            str_inter = str_para_intersecao(possivel_jog)
            #Temos de ter em conta que o input pode não ser convertível para uma intersecao
            if eh_intersecao(str_inter) and \
                eh_intersecao_valida(goban, str_inter):
            #Não pode ser uma str vazia e uma não interseção e possuir mais que uma letra no inicio
                inter = str_inter
                if eh_jogada_legal(goban, inter, ped, goban_proibido):
                    jogada(goban, inter, ped)
                    return True
        except ValueError:
            pass   

def go(dim,b_inic,p_inic):
    """Função que permite jogar o jogo Go
    
    Função principal que permite jogar um jogo de Go de dois jogadores.
    A função recebe um inteiro (dim) correspondente à dimensão do
    tabuleiro, e dois tuplos (potencialmente vazios) com a representação
    externa das interseções ocupadas por pedras brancas (b_inic) e pedras pretas
    (p_inic). O jogo termina quando os dois jogadores passam a vez consecutivamente.
    A função devolve True se o jogador ganhou ou False caso contrário.
        
    go: {int, tuple, tuple} --> booleano
        
    """
    def imprime(pb, pp, goban):
        print('Branco (O) tem {} pontos'.format(pb))
        print('Preto (X) tem {} pontos'.format(pp))    
        print((goban_para_str(goban)))           
    if type(b_inic) != tuple or type(p_inic) != tuple:
        raise ValueError("go: argumentos invalidos")
    try:
        inter_inic_b = tuple(str_para_intersecao(i) for i in  b_inic)
        inter_inic_p = tuple(str_para_intersecao(i) for i in  p_inic)
        goban = cria_goban(dim, inter_inic_b, inter_inic_p)
    except (TypeError,ValueError,IndexError) as error:
        raise ValueError("go: argumentos invalidos")
    jog_pretas = True
    jog_brancas = True
    while jog_pretas or jog_brancas:
    #Ambos precisam de passar para o jogo acabar
        (pontos_b, pontos_p) = calcula_pontos(goban)
        imprime(pontos_b, pontos_p, goban)
        goban_proib_b = cria_copia_goban(goban)
        jog_pretas = turno_jogador(goban, cria_pedra_preta(), goban_proib_b) 
        if jog_brancas or jog_pretas: 
        #Caso o jogador com peças brancas tenha passado e o preto tmb, o branco não pode jogar de novo
            (pontos_b, pontos_p) = calcula_pontos(goban)
            imprime(pontos_b, pontos_p, goban)
            goban_proib_p = cria_copia_goban(goban)
            jog_brancas = turno_jogador(goban, cria_pedra_branca(), goban_proib_b)
    imprime(pontos_b, pontos_p, goban)
    return pontos_b >= pontos_p
    
if __name__ == "__main__":
	size = 0
	while size not in (9,13,19):
		try:
			size = int(input("Write the size of the board (9, 13, 19):"))
		except (ValueError) as err:
			raise ValueError("input a number Ze")
	go(size, (), ())

