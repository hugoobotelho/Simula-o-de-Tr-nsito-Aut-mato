/* ***************************************************************
* Autor............: Hugo Botelho Santana
* Matricula........: 202210485
* Inicio...........: 20/11/2023
* Ultima alteracao.: 04/12/2023
* Nome.............: Simulacao de Transito/Trafego Automato
* Funcao...........: Simular um sistema de transito automoto
*************************************************************** */

//Importacao das bibliotecas do JavaFx

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.util.Duration;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;

// Classe principal que herda da classe Application
public class Principal extends Application {
  private static Pane root = new Pane();
  private Carros carro1;
  private Carros carro2;
  private Carros carro3;
  private Carros carro4;
  private Carros carro5;
  private Carros carro6;
  private Carros carro7;
  private Carros carro8;

  private static Image imageCarro1;
  private static ImageView imageViewCarro1;
  private static Image imageTrajetoCarro1;

  private static Image imageCarro2;
  private static ImageView imageViewCarro2;
  private static Image imageTrajetoCarro2;

  private static Image imageCarro3;
  private static ImageView imageViewCarro3;
  private static Image imageTrajetoCarro3;

  private static Image imageCarro4;
  private static ImageView imageViewCarro4;
  private static Image imageTrajetoCarro4;

  private static Image imageCarro5;
  private static ImageView imageViewCarro5;
  private static Image imageTrajetoCarro5;

  private static Image imageCarro6;
  private static ImageView imageViewCarro6;
  private static Image imageTrajetoCarro6;

  private static Image imageCarro7;
  private static ImageView imageViewCarro7;
  private static Image imageTrajetoCarro7;

  private static Image imageCarro8;
  private static ImageView imageViewCarro8;
  private static Image imageTrajetoCarro8;

  private static HBox hbox1;
  private static HBox hbox2;
  private static HBox hbox3;
  private static HBox hbox4;
  private static HBox hbox5;
  private static HBox hbox6;
  private static HBox hbox7;
  private static HBox hbox8;

  private static Button resetButton = new Button();

  private static VBox vbox;

  public void start(Stage primaryStage){

    Image backgroundImage = new Image("/img/mapa.png");
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(910);
    backgroundImageView.setFitHeight(700);
    root.getChildren().add(backgroundImageView);

    imageCarro1 = new Image("img/carro1.png");
    imageViewCarro1 = new ImageView(imageCarro1);
    imageViewCarro1.setFitWidth(25);
    imageViewCarro1.setFitHeight(45);
    imageTrajetoCarro1 = new Image("img/trajetoCarro1.png");
    carro1 = new Carros(imageCarro1, imageTrajetoCarro1, 50, -5, 1);

    imageCarro2 = new Image("img/carro2.png");
    imageViewCarro2 = new ImageView(imageCarro2);
    imageViewCarro2.setFitWidth(25);
    imageViewCarro2.setFitHeight(45);
    imageTrajetoCarro2 = new Image("img/trajetoCarro2.png");
    carro2 = new Carros(imageCarro2, imageTrajetoCarro2, 140, 570, 2);

    imageCarro3 = new Image("img/carro3.png");
    imageViewCarro3 = new ImageView(imageCarro3);
    imageViewCarro3.setFitWidth(25);
    imageViewCarro3.setFitHeight(45);
    imageTrajetoCarro3 = new Image("img/trajetoCarro3.png");
    carro3 = new Carros(imageCarro3, imageTrajetoCarro3, 230, 660, 3);

    imageCarro4 = new Image("img/carro4.png");
    imageViewCarro4 = new ImageView(imageCarro4);
    imageViewCarro4.setFitWidth(25);
    imageViewCarro4.setFitHeight(45);
    imageTrajetoCarro4 = new Image("img/trajetoCarro4.png");
    carro4 = new Carros(imageCarro4, imageTrajetoCarro4,270, 430, 4);

    imageCarro5 = new Image("img/carro5.png");
    imageViewCarro5 = new ImageView(imageCarro5);
    imageViewCarro5.setFitWidth(25);
    imageViewCarro5.setFitHeight(45);
    imageTrajetoCarro5 = new Image("img/trajetoCarro5.png");
    carro5 = new Carros(imageCarro5, imageTrajetoCarro5, 400, 440, 5); //400 480

    imageCarro6 = new Image("img/carro6.png");
    imageViewCarro6 = new ImageView(imageCarro6);
    imageViewCarro6.setFitWidth(25);
    imageViewCarro6.setFitHeight(45);
    imageTrajetoCarro6 = new Image("img/trajetoCarro6.png");
    carro6 = new Carros(imageCarro6, imageTrajetoCarro6, 660, 40, 6);

    imageCarro7 = new Image("img/carro7.png");
    imageViewCarro7 = new ImageView(imageCarro7);
    imageViewCarro7.setFitWidth(25);
    imageViewCarro7.setFitHeight(45);
    imageTrajetoCarro7 = new Image("img/trajetoCarro7.png");
    carro7 = new Carros(imageCarro7, imageTrajetoCarro7,100, 260, 7);

    imageCarro8 = new Image("img/carro8.png");
    imageViewCarro8 = new ImageView(imageCarro8);
    imageViewCarro8.setFitWidth(25);
    imageViewCarro8.setFitHeight(45);
    imageTrajetoCarro8 = new Image("img/trajetoCarro8.png");
    carro8 = new Carros(imageCarro8, imageTrajetoCarro8, 45, 130, 8);

    hbox1 = carro1.criarBotoes();
    hbox1.getChildren().add(imageViewCarro1);

    hbox2 = carro2.criarBotoes();
    hbox2.getChildren().add(imageViewCarro2);

    hbox3 = carro3.criarBotoes();
    hbox3.getChildren().add(imageViewCarro3);

    hbox4 = carro4.criarBotoes();
    hbox4.getChildren().add(imageViewCarro4);

    hbox5 = carro5.criarBotoes();
    hbox5.getChildren().add(imageViewCarro5);

    hbox6 = carro6.criarBotoes();
    hbox6.getChildren().add(imageViewCarro6);

    hbox7 = carro7.criarBotoes();
    hbox7.getChildren().add(imageViewCarro7);

    hbox8 = carro8.criarBotoes();
    hbox8.getChildren().add(imageViewCarro8);

    Image resetImage = new Image("/img/reset.png");
    ImageView resetImageView = new ImageView(resetImage);
    resetImageView.setFitWidth(50);
    resetImageView.setFitHeight(50);
    resetButton.setPrefWidth(150);
    resetButton.setGraphic(resetImageView);
    resetButton.setStyle("-fx-background-radius: 15;");
    resetButton.setOnMouseClicked(event -> {reset();});

    vbox = new VBox(15, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, resetButton);
    vbox.setLayoutX(710);
    vbox.setLayoutY(30);
    vbox.setAlignment(Pos.CENTER);


    root.getChildren().addAll(carro1.criarTrajeto(), carro2.criarTrajeto(), carro3.criarTrajeto(), carro4.criarTrajeto(), carro5.criarTrajeto(), carro6.criarTrajeto(), carro7.criarTrajeto(), carro8.criarTrajeto());

    root.getChildren().addAll(carro1.criarImagem(), carro2.criarImagem(), carro3.criarImagem(), carro4.criarImagem(), carro5.criarImagem(), carro6.criarImagem(), carro7.criarImagem(), carro8.criarImagem(), vbox);

    carro1.start();
    carro2.start();
    carro3.start();
    carro4.start();
    carro5.start();
    carro6.start();
    carro7.start();
    carro8.start();

  	Scene scene = new Scene(root, 900, 700);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Simulacao de Transito/Trafego Automato");
    //Bloqueio da maximizacao da tela
    primaryStage.setResizable(false);
    //Exibicao da janela
    primaryStage.show();

    primaryStage.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    });
  }

  /* ***************************************************************
  * Metodo: reset.
  * Funcao: faz com que os carros, botoes e semaforos voltem as configuraçoes iniciais.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void reset(){
    carro1.pararCarro();
    carro2.pararCarro();
    carro3.pararCarro();
    carro4.pararCarro();
    carro5.pararCarro();
    carro6.pararCarro();
    carro7.pararCarro();
    carro8.pararCarro();

    carro1.interrupt();
    carro2.interrupt();  
    carro3.interrupt();  
    carro4.interrupt();  
    carro5.interrupt();  
    carro6.interrupt();
    carro7.interrupt();
    carro8.interrupt();   

    iniciar(); 
  }

  /* ***************************************************************
  * Metodo: iniciar.
  * Funcao: remove os elementos do root, instancia novos carros, cria os botoes e da start nos carros.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void iniciar(){

    root.getChildren().removeAll(carro1.criarTrajeto(), carro2.criarTrajeto(), carro3.criarTrajeto(), carro4.criarTrajeto(), carro5.criarTrajeto(), carro6.criarTrajeto(), carro7.criarTrajeto(), carro8.criarTrajeto());

    root.getChildren().removeAll(carro1.criarImagem(), carro2.criarImagem(), carro3.criarImagem(), carro4.criarImagem(), carro5.criarImagem(), carro6.criarImagem(), carro7.criarImagem(), carro8.criarImagem(), vbox);

    carro1 = new Carros(imageCarro1, imageTrajetoCarro1, 50, -5, 1);
    carro2 = new Carros(imageCarro2, imageTrajetoCarro2, 140, 570, 2);
    carro3 = new Carros(imageCarro3, imageTrajetoCarro3, 230, 660, 3);
    carro4 = new Carros(imageCarro4, imageTrajetoCarro4,270, 430, 4);
    carro5 = new Carros(imageCarro5, imageTrajetoCarro5, 400, 440, 5);
    carro6 = new Carros(imageCarro6, imageTrajetoCarro6, 660, 40, 6);
    carro7 = new Carros(imageCarro7, imageTrajetoCarro7,100, 260, 7);
    carro8 = new Carros(imageCarro8, imageTrajetoCarro8, 45, 130, 8);

    hbox1 = carro1.criarBotoes();
    hbox1.getChildren().add(imageViewCarro1);

    hbox2 = carro2.criarBotoes();
    hbox2.getChildren().add(imageViewCarro2);

    hbox3 = carro3.criarBotoes();
    hbox3.getChildren().add(imageViewCarro3);

    hbox4 = carro4.criarBotoes();
    hbox4.getChildren().add(imageViewCarro4);

    hbox5 = carro5.criarBotoes();
    hbox5.getChildren().add(imageViewCarro5);

    hbox6 = carro6.criarBotoes();
    hbox6.getChildren().add(imageViewCarro6);

    hbox7 = carro7.criarBotoes();
    hbox7.getChildren().add(imageViewCarro7);

    hbox8 = carro8.criarBotoes();
    hbox8.getChildren().add(imageViewCarro8);

    vbox = new VBox(15, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, resetButton);
    vbox.setLayoutX(710);
    vbox.setLayoutY(30);
    vbox.setAlignment(Pos.CENTER);

    root.getChildren().addAll(carro1.criarTrajeto(), carro2.criarTrajeto(), carro3.criarTrajeto(), carro4.criarTrajeto(), carro5.criarTrajeto(), carro6.criarTrajeto(), carro7.criarTrajeto(), carro8.criarTrajeto());

    root.getChildren().addAll(carro1.criarImagem(), carro2.criarImagem(), carro3.criarImagem(), carro4.criarImagem(), carro5.criarImagem(), carro6.criarImagem(), carro7.criarImagem(), carro8.criarImagem(), vbox);

    iniciarSemaforos();

    carro1.start();
    carro2.start();
    carro3.start();
    carro4.start();
    carro5.start();
    carro6.start();
    carro7.start();
    carro8.start();
  }

  /* ***************************************************************
  * Metodo: iniciarSemaforos.
  * Funcao: coloca os valores iniciais nos semaforos de novo.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public static void iniciarSemaforos(){
  Semaforos.cruzamento1 = new Semaphore(1);
  Semaforos.cruzamento2 = new Semaphore(1);
  Semaforos.cruzamento3 = new Semaphore(1);
  Semaforos.cruzamento4 = new Semaphore(1);
  Semaforos.cruzamento5 = new Semaphore(1);
  Semaforos.cruzamento6 = new Semaphore(1);
  Semaforos.cruzamento7 = new Semaphore(1);
  Semaforos.cruzamento8 = new Semaphore(1);
  Semaforos.cruzamento9 = new Semaphore(1);
  Semaforos.cruzamento10 = new Semaphore(1);
  Semaforos.cruzamento11 = new Semaphore(1);
  Semaforos.cruzamento12 = new Semaphore(1);
  Semaforos.cruzamento13 = new Semaphore(1);
  Semaforos.cruzamento14 = new Semaphore(1);
  Semaforos.cruzamento15 = new Semaphore(1);
  Semaforos.cruzamento16 = new Semaphore(1);
  Semaforos.cruzamento17 = new Semaphore(1);
  Semaforos.cruzamento18 = new Semaphore(1);
  Semaforos.cruzamento19 = new Semaphore(1);
  Semaforos.cruzamento20 = new Semaphore(1);
  Semaforos.cruzamento21 = new Semaphore(1);
  Semaforos.cruzamento22 = new Semaphore(1);
  Semaforos.cruzamento23 = new Semaphore(1);
  Semaforos.cruzamento24 = new Semaphore(1);
  Semaforos.cruzamento25 = new Semaphore(1);
  Semaforos.cruzamento26 = new Semaphore(1);
  Semaforos.cruzamento27 = new Semaphore(1);
  Semaforos.cruzamento28 = new Semaphore(1);
  Semaforos.cruzamento29 = new Semaphore(1);
  Semaforos.cruzamento30 = new Semaphore(1);
  Semaforos.cruzamento31 = new Semaphore(1);
  Semaforos.cruzamento32 = new Semaphore(1);

  Semaforos.rua12a = new Semaphore(0); //inicia com 0 pois o carro 1 ja comeca na regiao critica
  Semaforos.rua12b = new Semaphore(1);
  Semaforos.rua12c = new Semaphore(1);
  Semaforos.rua12d = new Semaphore(1);
  Semaforos.rua13a = new Semaphore(0); //Inicia com 0 pois o carro 3 ja comeca na reigão critica
  Semaforos.rua14a = new Semaphore(0); //inicia com 0 pois o carro 1 ja comeca na região critica
  Semaforos.rua15a = new Semaphore(1);
  Semaforos.rua16a = new Semaphore(1);
  Semaforos.rua17a = new Semaphore(1);
  Semaforos.rua17b = new Semaphore(1);
  Semaforos.rua17c = new Semaphore(1);
  Semaforos.rua17d = new Semaphore(1);
  Semaforos.rua18a = new Semaphore(1);
  Semaforos.rua18b = new Semaphore(1);
  Semaforos.rua18c = new Semaphore(1);
  Semaforos.rua18d = new Semaphore(1);

  Semaforos.rua23a = new Semaphore(1);
  Semaforos.rua23b = new Semaphore(1);
  Semaforos.rua23c = new Semaphore(1);
  Semaforos.rua23d = new Semaphore(1);
  Semaforos.rua24a = new Semaphore(1);
  Semaforos.rua24b = new Semaphore(1);
  Semaforos.rua25a = new Semaphore(1);
  Semaforos.rua25b = new Semaphore(1);
  Semaforos.rua26a = new Semaphore(1);
  Semaforos.rua26b = new Semaphore(0); //inicia com zero pois o carro 6 ja comeca na regiao critica
  Semaforos.rua26c = new Semaphore(1);
  Semaforos.rua26d = new Semaphore(1);
  Semaforos.rua27a = new Semaphore(1);
  Semaforos.rua27b = new Semaphore(1);
  Semaforos.rua27c = new Semaphore(1);
  Semaforos.rua27d = new Semaphore(1);
  Semaforos.rua27e = new Semaphore(1);
  Semaforos.rua27f = new Semaphore(1);
  Semaforos.rua28a = new Semaphore(1);
  Semaforos.rua28b = new Semaphore(1);
  Semaforos.rua28c = new Semaphore(1);
  Semaforos.rua28d = new Semaphore(1);

  Semaforos.rua34a = new Semaphore(0); //inicia com zero pois o carro 3 ja comeca na regiao critica
  Semaforos.rua35a = new Semaphore(1);
  Semaforos.rua36a = new Semaphore(1);
  Semaforos.rua37a = new Semaphore(1);
  Semaforos.rua37b = new Semaphore(1);
  Semaforos.rua37c = new Semaphore(1);
  Semaforos.rua38a = new Semaphore(1);
  Semaforos.rua38b = new Semaphore(1);
  Semaforos.rua38c = new Semaphore(1);

  Semaforos.rua46a = new Semaphore(1);
  Semaforos.rua47a = new Semaphore(1);
  Semaforos.rua47b = new Semaphore(1);
  Semaforos.rua47c = new Semaphore(1);
  Semaforos.rua48a = new Semaphore(1);
  Semaforos.rua48b = new Semaphore(1);
  Semaforos.rua48c = new Semaphore(1);

  Semaforos.rua56a = new Semaphore(0); //inicia com zero pois o carro 6 ja comeca na regiao critica
  Semaforos.rua57a = new Semaphore(1);
  Semaforos.rua57b = new Semaphore(1);
  Semaforos.rua57c = new Semaphore(1);
  Semaforos.rua58a = new Semaphore(1);
  Semaforos.rua58b = new Semaphore(1);
  Semaforos.rua58c = new Semaphore(1);

  Semaforos.rua67a = new Semaphore(1);
  Semaforos.rua67b = new Semaphore(1);
  Semaforos.rua68a = new Semaphore(1);
  Semaforos.rua68b = new Semaphore(1);

  Semaforos.rua78a = new Semaphore(1);
  Semaforos.rua78b = new Semaphore(1);
  Semaforos.rua78c = new Semaphore(1);
  Semaforos.rua78d = new Semaphore(1);
  }

  /* ***************************************************************
  * Metodo: main.
  * Funcao: metodo para iniciar a aplicacao.
  * Parametros: padrao java.
  * Retorno: sem retorno.
  *************************************************************** */
  public static void main(String[] args){
    launch(args);
  }
}