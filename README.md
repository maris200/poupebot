# Aplicativo Poupe Bot

Aplicativo Poupe bot foi criado para usuários que tem como objetivo acompanhar seus gastos finânceiros.

#Gráfico:
Com nosso gráfico os usuários poderão visualizar todas suas receitas ou despesas de acordo com mês/ano.

#Objetivos:
Nosso aplicativo ajudar nossos usuarios gerenciar todos seus objetivos financeiros utilizamos a gamificação para tornar a usabilidade do nosso aplicativo mais interativa.

#Chatbot:
Criamos um chatbot utilizando o IBM Watsom para nossos usuários tirar dúvidas de como usar o aplicativo, e consultar informações finânceiras.

#Desenvolvedores:

Eduardo Augusto Petreca, Marília Vilas Boas

#Informações técnicas

Fragments

Implementamos a utilização de fragmento pensando na experiência de usuário tornando uma navegação mais simples e rápida

MVP

O MVP é uma evolução do MVC que se comunica bidirecionalmente com as outras camadas, evitando que o Model tenha que se comunicar diretamente com a View sem passar pelo Controller e este último é fundamental para a interação com o usuário. O MVP desacopla as funções e torna a arquitetura ainda mais modular.

A camada Presenter é ciente de tudo o que ocorre nas outras duas camadas e deixa-as cientes do que ela está fazendo
a interação com usuário é feita primariamente pela View, e esta pode delegar ao Presenter determinadas tarefas
há uma relação um-para-um entre estas camadas. Nesta relação há uma referência do View para o Presenter mas não o oposto.

#Site Oficial:

http://www.poupebot.16mb.com

#Download Playstore:

https://play.google.com/store/apps/details?id=br.com.megaapps.mepoupe

Bibliotecas utilizadas neste projeto:

Front end:

     'com.android.support:appcompat-v7:25.3.1'
     'com.android.support:cardview-v7:25.3.1'
     'com.android.support.constraint:constraint-layout:1.0.1'
     'com.android.support:support-v4:25.3.1'
     'com.android.support:design:25.3.1'
     'com.weiwangcn.betterspinner:library-material:1.1.0'
     'de.hdodenhof:circleimageview:1.3.0'
     'com.getbase:floatingactionbutton:1.10.1'

Leitura de webservice: 

     'com.squareup.retrofit2:retrofit:2.3.0'
     'com.squareup.retrofit2:converter-gson:2.3.0'
     'com.google.code.gson:gson:2.8.0'
     'com.squareup.okhttp3:okhttp:3.6.0'

Obter resultado da OnActiviteResult em fragmentos:

     'com.squareup:otto:1.3.6'

Gráfico

     'com.github.PhilJay:MPAndroidChart:v3.0.2'

IBM Watsom 

     'com.ibm.watson.developer_cloud:conversation:3.7.2'
     'com.ibm.watson.developer_cloud:android-sdk:0.2.3'
     
     




   


