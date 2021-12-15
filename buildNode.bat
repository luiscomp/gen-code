@CHCP 65001 >NUL
cd /d "C:\Users\Luis Eduardo\Documents\Meus Arquivos\Programação\Node\eatGo\eatgo_Node"
call npm install
@echo Instalação Backend concluída.
@echo.
cd /d "C:\Users\Luis Eduardo\Documents\Meus Arquivos\Programação\Node\eatGo\eatgo_Vue"
call npm install
@echo Instalação Frontend concluída.
@echo.
@pause
exit