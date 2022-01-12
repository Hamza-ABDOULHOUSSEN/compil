import sys

start_buffer = ""
buffer = ""
end_buffer = "</div>\n</body>\n</html>\n"

compt = 0
juste = 0

def start_buffer_write(start_buffer, path):
    fichier = open("html_generator/start_template.html", "r", encoding="utf-8")
    k=0
    lines = fichier.readlines()
    for line in lines:
        if (k==6):
            start_buffer = start_buffer + '<title>'+'Test erreur sémantique'+'</title>'
        start_buffer += line
        k+=1
    fichier.close()
    return start_buffer

def add_title(buffer, basename):
    buffer = buffer + '\t<h2><span style="color: #3366ff;">Test : ' + basename + ' ('+str(juste)+'/'+str(compt)')</span></h2>\n\n'
    return buffer

def add_code(buffer, file):
    buffer = buffer + '\t<p>&nbsp;</p>\n\t<h3 id="code"><span style="color: #333333;">Code</span></h3>\n\t<p>&nbsp;</p>\n\t<div class="alert alert-secondary" role="alert">\n\t<p>'
    filepath = "examples/" + file
    fichier = open(filepath, "r", encoding="utf-8")
    lines = fichier.readlines()
    for line in lines:
        buffer = buffer + "\t\t<pre>"+ line[:-1] +"</pre>\n"

    buffer = buffer + "\t</p>\n\t</div>\n\n"

    fichier.close()
    return buffer

def add_erreur(buffer, file):
    buffer = buffer + '\t<p>&nbsp;</p>\n\t<h3 id="error"><span style="color: #333333;">Erreur</span></h3>\n\t<p>&nbsp;</p>\n\t<div class="alert alert-secondary" role="alert">\n\t<p>'
    fichier = open(file, "r", encoding="utf-8")
    lines = fichier.readlines()
    if len(lines)==0:
        buffer = buffer + '\t\t<pre style="color: green;">Aucune erreur</pre>\n'
    else:
        for line in lines:
            buffer = buffer + '\t\t<pre style="color: red;">'+ line[5:-5] +'</pre>\n'

    buffer = buffer + "\t</p>\n\t</div>\n\n"

    fichier.close()
    return buffer

def add_ast(buffer, file):
    buffer = buffer + '\t<p>&nbsp;</p>\n\t<h3 id="ast"><span style="color: #333333;">AST</span></h3>\n\t<p>&nbsp;</p>\n\t<div class="img-container">\n'
    buffer = buffer + '\t\t<img style="width:auto; height=auto;" src="../../out/ast/svg/'+ file + '.svg" class="table" >\n'
    buffer = buffer +  '\t</div>\n'
    return buffer

def add_tds(buffer, file):
    buffer = buffer + '\t<p>&nbsp;</p>\n\t<h3 id="tds"><span style="color: #333333;">TDS</span></h3>\n\t<p>&nbsp;</p>\n\t<div class="img-container">\n'
    buffer = buffer + '\t\t<img style="width:auto; height=auto;" src="../../out/tds/svg/'+ file + '.svg" class="table" >\n'
    buffer = buffer +  '\t</div>\n'
    return buffer


args = sys.argv[1:]
path=args[0]
erreur_path="temp2"

start_buffer = start_buffer_write(start_buffer, path)
buffer = add_title(buffer, path+".exp")
buffer = add_code(buffer, path+".exp")
buffer = add_erreur(buffer, erreur_path)
buffer = add_ast(buffer, path)
buffer = add_tds(buffer, path)

html_file = "out/html/"+path+".html"
fichier=open(html_file, "w")
fichier.write(start_buffer + buffer + end_buffer)
fichier.close()



