/* ***************************************************************
* Autor............: Hugo Botelho Santana
* Matricula........: 202210485
* Inicio...........: 20/11/2023
* Ultima alteracao.: 04/12/2023
* Nome.............: Simulacao de Transito/Trafego Automato
* Funcao...........: Simular um sistema de transito automoto
*************************************************************** */

//Importacao das bibliotecas do JavaFx

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import java.util.concurrent.Semaphore;

public class Carros extends Thread {
  private Image imageCarro;
  private ImageView imageViewCarro;
  private Image imageTrajeto;
  private ImageView imageViewTrajeto;
  private double posX;
  private double posY;
  private long speedFactor = 10;
  private int cont = 43;
  private boolean parar = false;
  private int id;
  private Slider sliderVelocidade;

  public Carros(Image imageCarro, Image imageTrajeto, double posX, double posY, int id) {
    this.imageCarro = imageCarro;
    this.imageViewCarro = new ImageView(imageCarro);
    imageViewCarro.setFitWidth(25);
    imageViewCarro.setFitHeight(45);

    this.imageTrajeto = imageTrajeto;
    this.imageViewTrajeto = new ImageView(imageTrajeto);
    imageViewTrajeto.setFitWidth(910);
    imageViewTrajeto.setFitHeight(700);

    this.posX = posX;
    this.posY = posY;    
    Platform.runLater(() -> {
  	  imageViewCarro.setLayoutX(posX);
  	  imageViewCarro.setLayoutY(posY);
    });
    try {
        Thread.sleep(50);
    } catch (InterruptedException e) {
      //e.printStackTrace();
    }
    this.id = id;
  }

  public void run() {
    while (true) {
      switch(id){
        case 1:
          percursoCarro1();
          break;
        case 2:
          percursoCarro2();
          break;
        case 3:
          percursoCarro3();
          break;
        case 4:
          percursoCarro4();
          break;
        case 5:
          percursoCarro5();
          break;
        case 6:
          percursoCarro6();
          break;
        case 7:
          percursoCarro7();
          break;
        case 8:
          percursoCarro8();
          break;
      }
    }
  }
  
  /* ***************************************************************
  * Metodo: criarImagem.
  * Funcao: apenas retorna a imagem do carro para ser adicionada ao Pane root na principal.
  * Parametros: nenhum.
  * Retorno: retorna o imageViewCarro.
  *************************************************************** */
  public ImageView criarImagem() {
    return imageViewCarro;
  }

  /* ***************************************************************
  * Metodo: criarBotoes.
  * Funcao: cria a parte grafica e logica dos botoes e retorna um hbox contendo os elementos dentro dele para ser adicionado ao Pane.
  * Parametros: nenhum.
  * Retorno: retorna o hbox.
  *************************************************************** */
  public HBox criarBotoes(){
  	sliderVelocidade = new Slider(0, 20, speedFactor);
  	sliderVelocidade.setShowTickLabels(false);// Remove os numeros abaixo do Slider
  	sliderVelocidade.setShowTickMarks(true);  // Mantem as marcas (indicadores) no Slider
  	sliderVelocidade.setMajorTickUnit(5);     // Configura o salto do Slider para ser de cinco em cinco
  	sliderVelocidade.setMinorTickCount(0);    // Desativa as marcas menores
  	//sliderVelocidade.setSnapToTicks(true);    // Faz com que o Slider "pule" para as marcas (ticks)
  	sliderVelocidade.valueProperty().addListener((observable, oldValue, newValue) -> {
        double sliderValue = newValue.doubleValue();

        if (sliderValue >= 15) {
          // Se o valor do slider for maior ou igual a 15, ajuste o speedFactor para se aproximar de 1
          speedFactor = (long) (1 + 0.2 * Math.round((20 - sliderValue) / 5));
        } else {
          // Se o valor do slider for menor que 15, use a logica original
          speedFactor = 20 - 5 * Math.round(sliderValue / 5);
        }
  	});

  	HBox botoesVelocidade = new HBox(5, sliderVelocidade); // Adicione o Slider ao layout
    botoesVelocidade.setAlignment(Pos.CENTER);

    Image pauseImage = new Image("/img/pause.png");
    ImageView pauseImageView = new ImageView(pauseImage);
    pauseImageView.setFitWidth(15);
    pauseImageView.setFitHeight(15);

    Button parar = new Button();
    parar.setGraphic(pauseImageView);
    parar.setOnMouseClicked(event -> {pararCarro();});

    Image playImage = new Image("/img/play.png");
    ImageView playImageView = new ImageView(playImage);
    playImageView.setFitWidth(15);
    playImageView.setFitHeight(15);

    Button retomar = new Button();
    retomar.setGraphic(playImageView);
    retomar.setOnMouseClicked(event -> {retomarCarro();});    

    Image rotaImage = new Image("/img/location.png");
    ImageView rotaImageView = new ImageView(rotaImage);
    rotaImageView.setFitWidth(15);
    rotaImageView.setFitHeight(15);

    ToggleButton rota = new ToggleButton();
    rota.setGraphic(rotaImageView);
    // Configurar a acao do ToggleButton
    rota.setOnAction(event -> {
      if (rota.isSelected()) {
        // Se o ToggleButton estiver pressionado, mostrar a imagem
        imageViewTrajeto.setVisible(true);
      } else {
        // Se o ToggleButton nao estiver pressionado, esconder a imagem
        imageViewTrajeto.setVisible(false);
      }
    });

    HBox botoesPausaRetomadaRota = new HBox(5, parar, retomar, rota);
    botoesPausaRetomadaRota.setAlignment(Pos.CENTER);

    VBox vbox = new VBox(botoesVelocidade, botoesPausaRetomadaRota);
    vbox.setAlignment(Pos.CENTER);

    HBox hbox = new HBox(5, vbox);
    return hbox;
  }

  /* ***************************************************************
  * Metodo: pararCarro.
  * Funcao: troca o valor da variavel parar para true.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void pararCarro(){
  	parar = true;
  }
 
  /* ***************************************************************
  * Metodo: retomarCarro.
  * Funcao: troca o valor da variavel parar para false.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void retomarCarro(){
  	parar = false;
  }

  /* ***************************************************************
  * Metodo: criarTrajeto.
  * Funcao: deixa o imageViewTrajeto com visibilidade falsa.
  * Parametros: nenhum.
  * Retorno: retorna o imageViewTrajeto.
  *************************************************************** */
  public ImageView criarTrajeto(){
      Platform.runLater(() -> {
        imageViewTrajeto.setVisible(false);
      });
      try {
          Thread.sleep(100);  // Adiciona um pequeno intervalo entre as iteracoes
      } catch (InterruptedException e) {
          //e.printStackTrace();
      }
    return imageViewTrajeto;
  }

  /* ***************************************************************
  * Metodo: esquerda.
  * Funcao: faz com que o carro ande para a esquerda.
  * Parametros: nenhum.
  * Retorno: Sem retorno.
  *************************************************************** */
  public void esquerda() {
    Platform.runLater(() -> {
      imageViewCarro.setRotate(270);
    });
    for (int i = 0; i < cont; i++){
      try {
          Thread.sleep(speedFactor);  // Adiciona um pequeno intervalo entre as iteracoes
      } catch (InterruptedException e) {
          //e.printStackTrace();
      }
      while(parar == true){
	      try {
	          Thread.sleep(100);  // Adiciona um pequeno intervalo entre as iteracoes
	      } catch (InterruptedException e) {
	          //e.printStackTrace();
	      }
      }
      posX -= 1;
      Platform.runLater(() -> {
        imageViewCarro.setLayoutX(posX);
      });
    }
  }

  /* ***************************************************************
  * Metodo: direita.
  * Funcao: faz com que o carro ande para a direita.
  * Parametros: nenhum.
  * Retorno: Sem retorno.
  *************************************************************** */
  public void direita() {
    Platform.runLater(() -> {
      imageViewCarro.setRotate(90);
    });
    for (int i = 0; i < cont; i++){
      try {
          Thread.sleep(speedFactor);  // Adiciona um pequeno intervalo entre as iteracoes
      } catch (InterruptedException e) {
          //e.printStackTrace();
      }
      while(parar == true){
	      try {
	          Thread.sleep(100);  // Adiciona um pequeno intervalo entre as iteracoes
	      } catch (InterruptedException e) {
	          //e.printStackTrace();
	      }
      }
      posX += 1;
      Platform.runLater(() -> {
        imageViewCarro.setLayoutX(posX);
      });
    }
  }

  /* ***************************************************************
  * Metodo: cima.
  * Funcao: faz com que o carro ande para cima.
  * Parametros: nenhum.
  * Retorno: Sem retorno.
  *************************************************************** */
  public void cima() {
    Platform.runLater(() -> {
      imageViewCarro.setRotate(0);
	  });
    for (int i = 0; i < cont; i++){
      try {
          Thread.sleep(speedFactor);  // Adiciona um pequeno intervalo entre as iteracoes
      } catch (InterruptedException e) {
          //e.printStackTrace();
      }
      while(parar == true){
	      try {
	          Thread.sleep(100);  // Adiciona um pequeno intervalo entre as iteracoes
	      } catch (InterruptedException e) {
	          //e.printStackTrace();
	      }
      }
      posY -= 1;
      Platform.runLater(() -> {
        imageViewCarro.setLayoutY(posY);
      });
    }
  }

  /* ***************************************************************
  * Metodo: baixo.
  * Funcao: faz com que o carro ande para baixo.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void baixo() {
    Platform.runLater(() -> {
      imageViewCarro.setRotate(180);
    });
    for (int i = 0; i < cont; i++){
      try {
          Thread.sleep(speedFactor);  // Adiciona um pequeno intervalo entre as iteracoes
      } catch (InterruptedException e) {
          //e.printStackTrace();
      }
      while(parar == true){
	      try {
	          Thread.sleep(100);  // Adiciona um pequeno intervalo entre as iteracoes
	      } catch (InterruptedException e) {
	          //e.printStackTrace();
	      }
      }
      posY += 1;
      Platform.runLater(() -> {
        imageViewCarro.setLayoutY(posY);
      });
    }
  }


  /* ***************************************************************
  * Metodo: percursoCarro1.
  * Funcao: faz o carro1 percorrer o seu percurso sem haver colisao com outro carro.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void percursoCarro1() {
  	direita();
    //cruzamento1    
      try {
  	    Semaforos.cruzamento1.acquire();
  	  	direita();
  	  	direita();
  	  	Semaforos.cruzamento1.release();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restaura o status de interrupcao
      }
  	//fim do cruzamento1
  	direita();
    //regiao critica 16a
    //regiao critica 17a
    //regiao critica 18a
    try{
      Semaforos.rua16a.acquire();
      Semaforos.rua17a.acquire();
      Semaforos.rua18a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento2
    	try {
  	    Semaforos.cruzamento2.acquire();
  	  	direita();
  	  	baixo();
  	  	Semaforos.cruzamento2.release();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restaura o status de interrupcao
      }
  	//fim do cruzamento2
  	baixo();
    //cruzamento7
      try {
        Semaforos.cruzamento7.acquire();
        baixo();
        baixo();
        Semaforos.cruzamento7.release();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restaura o status de interrupcao
      }
    //fim do cruzamento7
    Semaforos.rua18a.release();
    //fim da regiao critica 18a
    Semaforos.rua17a.release();
    //fim da regiao critica 17a
    Semaforos.rua12a.release();
    //fim da regiao critica 12a
  	baixo();
    //regiao critica 12b
    try {
      Semaforos.rua12b.acquire();
      //cruzamento13
      try {
        Semaforos.cruzamento13.acquire();
        baixo();
        baixo();
        Semaforos.cruzamento13.release();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restaura o status de interrupcao
      }
      //fim do cruzamento13
      baixo(); 
      //regiao critica 13a
      try {
        Semaforos.rua13a.acquire();
        //cruzamento19
          try {
            Semaforos.cruzamento19.acquire();
            baixo();
            direita();
            Semaforos.cruzamento19.release();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt(); // Restaura o status de interrupcao
          }
        //fim do cruzamento19
        //libera regiao critica 14a
        Semaforos.rua14a.release();
        Semaforos.rua12b.release();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restaura o status de interrupcao
      }
      //fim da regiao critica 12b 
      direita();
      //regiao critica 12c
      try {
        Semaforos.rua12c.acquire();
        //cruzamento20
          try {
            Semaforos.cruzamento20.acquire();
            direita();
            direita();
            Semaforos.cruzamento20.release();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt(); // Restaura o status de interrupcao
          }
        //fim do cruzamento20
        direita();
        //regiao critica 17b
        Semaforos.rua17b.acquire();
        //cruzamento21
          try {
            Semaforos.cruzamento21.acquire();
            direita();
            direita();
            Semaforos.cruzamento21.release();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt(); // Restaura o status de interrupcao
          }
        //fim do cruzamento 21      
        Semaforos.rua12c.release();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restaura o status de interrupcao
      }
      //fim da regiao critica 12c
      direita();
      //regiao critica 12d
      try {
        Semaforos.rua12d.acquire();
        Semaforos.rua15a.acquire();
        Semaforos.rua18b.acquire();
        //cruzamento22
          try {
            Semaforos.cruzamento22.acquire();
            direita();
            baixo();
            Semaforos.cruzamento22.release();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt(); // Restaura o status de interrupcao
          }
        //fim do cruzamento22
        Semaforos.rua17b.release();
        Semaforos.rua16a.release();
        //fim da regiao critica 16a
        baixo();
        //cruzamento28
          try {
            Semaforos.cruzamento28.acquire();
            baixo();
            baixo();
            Semaforos.cruzamento28.release();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt(); // Restaura o status de interrupcao
          }
        //fim do cruzamento28
        Semaforos.rua18b.release();
        baixo();
        //extremo inferior direito
          baixo();
          esquerda();
        //fim do extremo inferior direito
        esquerda();
        //cruzamento32
          try {
            Semaforos.cruzamento32.acquire();
            esquerda();
            esquerda();
            Semaforos.cruzamento32.release();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt(); // Restaura o status de interrupcao
          }
        //fim do cruzamento32
        Semaforos.rua12d.release();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restaura o status de interrupcao
      }
      //fim da regiao critica 12d
      esquerda();
      //regiao critica 17c
      Semaforos.rua17c.acquire();
      Semaforos.rua18c.acquire();
      //cruzamento31
        try {
          Semaforos.cruzamento31.acquire();
          esquerda();
          esquerda();
          Semaforos.cruzamento31.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura o status de interrupcao
        }
      //fim do cruzamento31
      Semaforos.rua15a.release();
      esquerda();
      //regiao critica 14a
      Semaforos.rua14a.acquire();
      //cruzamento30
        try {
          Semaforos.cruzamento30.acquire();
          esquerda();
          esquerda();
          Semaforos.cruzamento30.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura o status de interrupcao
        }
      //fim do cruzamento30
      Semaforos.rua18c.release();
      Semaforos.rua17c.release();
      esquerda();
      //regiao critica 12a      
      try {
        Semaforos.rua12a.acquire();
        //cruzamento29
          try {
            Semaforos.cruzamento29.acquire();
            esquerda();
            esquerda();
            Semaforos.cruzamento29.release();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt(); // Restaura o status de interrupcao
          }
        //fim do cruzamento29
        esquerda();
        //extremo inferior esquerdo
          esquerda();
          cima();
        //fim do extremo inferior esquerdo
        cima();
        Semaforos.rua18d.acquire();
        //cruzamento23
          try {
            Semaforos.cruzamento23.acquire();
            cima();
            cima();
            Semaforos.cruzamento23.release();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt(); // Restaura o status de interrupcao
          }
        //fim do cruzamento23
        cima();
        //regiao critica rua 17d
        Semaforos.rua17d.acquire();
        //cruzamento17
          try {
            Semaforos.cruzamento17.acquire();
            cima();
            cima();
            Semaforos.cruzamento17.release();
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt(); // Restaura o status de interrupcao
          }
        //fim do cruzamento17        
        Semaforos.rua13a.release();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restaura o status de interrupcao
      }
      cima();
      //cruzamento11
        try {
          Semaforos.cruzamento11.acquire();
          cima();
          cima();
          Semaforos.cruzamento11.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura o status de interrupcao
        }
      //fim do cruzamento11
      Semaforos.rua17d.release();
      cima();
      //cruzamento5
        try {
          Semaforos.cruzamento5.acquire();
          cima();
          cima();
          Semaforos.cruzamento5.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura o status de interrupcao
        }
      //fim do cruzamento5
      Semaforos.rua18d.release();
      cima();
      //extremo superior esquerdo
        cima();
        direita();
      //fim do extremo superior esquerdo
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restaura o status de interrupcao
      }
    //fim da regiao critica 12a

  }

  /* ***************************************************************
  * Metodo: percursoCarro2.
  * Funcao: faz o carro2 percorrer o seu percurso sem haver colisao com outro carro.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void percursoCarro2(){
  	baixo();
    //regiao critica rua12a
    //regiao critica rua23a
    //regiao criticia rua24a
    try {
      Semaforos.rua12a.acquire();
      Semaforos.rua23a.acquire();
      Semaforos.rua24a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento29
  	try {
	    Semaforos.cruzamento29.acquire();
	  	baixo();
	  	esquerda();
	  	Semaforos.cruzamento29.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento29
  	esquerda();
  	//canto inferior esquerdo
  	esquerda();
  	cima();
  	//fim do cando inferior esquerdo
  	cima();
    //regiao critica rua28a
    try {
      Semaforos.rua28a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento23
  	try {
	    Semaforos.cruzamento23.acquire();
	  	cima();
	  	cima();
	  	Semaforos.cruzamento23.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento23
  	cima();
    //regiao critica rua27a
    try {
      Semaforos.rua27a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento17
  	try {
	    Semaforos.cruzamento17.acquire();
	  	cima();
	  	cima();
	  	Semaforos.cruzamento17.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento17
    Semaforos.rua23a.release();
    //fim da regiao critica 23a
  	cima();
  	//cruzamento11
  	try {
	    Semaforos.cruzamento11.acquire();
	  	cima();
	  	cima();
	  	Semaforos.cruzamento11.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento11
    Semaforos.rua27a.release();
    //fim da regiao critica 27a
  	cima();
  	//cruzamento5
  	try {
	    Semaforos.cruzamento5.acquire();
	  	cima();
	  	cima();
	  	Semaforos.cruzamento5.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento5
    Semaforos.rua28a.release();
    //fim da regiao critica 28a
  	cima();
  	//canto superior esquerdo
  	cima();
  	direita();
  	//fim do canto superior esquerdo
  	direita();
  	//cruzamento1
  	try {
	    Semaforos.cruzamento1.acquire();
	  	direita();
	  	direita();
	  	Semaforos.cruzamento1.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento1
  	direita();
    //regiao critica rua26a
    //regiao critica rua27b
    //regiao critica rua28b
    try {
      Semaforos.rua26a.acquire();
      Semaforos.rua27b.acquire();
      Semaforos.rua28b.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento2
  	try {
	    Semaforos.cruzamento2.acquire();
	  	direita();
	  	baixo();
	  	Semaforos.cruzamento2.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento2
  	baixo();
  	//cruzamento7
  	try {
	    Semaforos.cruzamento7.acquire();
	  	baixo();
	  	direita();
	  	Semaforos.cruzamento7.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento7
    Semaforos.rua28b.release();
    //fim da regiao critica 28b
    Semaforos.rua27b.release();
    //fim da regiao critica 27b
    Semaforos.rua26a.release();
    //fim da regiao critica 26a
    Semaforos.rua24a.release();
    //fim da regiao critica 24a
    Semaforos.rua12a.release();
    //fim da regiao critica 12a
  	direita();
    //regiao critica rua25a
    //regiao critica rua27c
    //regiao critica rua28c
    try {
      Semaforos.rua25a.acquire();
      Semaforos.rua27c.acquire();
      Semaforos.rua28c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento8
  	try {
	    Semaforos.cruzamento8.acquire();
	  	direita();
	  	cima();
	  	Semaforos.cruzamento8.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento8
  	cima();
    //regiao critica rua26b
    try {
      Semaforos.rua26b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento3
  	try {
	    Semaforos.cruzamento3.acquire();
	  	cima();
	  	direita();
	  	Semaforos.cruzamento3.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento3
    Semaforos.rua28c.release();
    //fim da regiao critica 28c
    Semaforos.rua27c.release();
    //fim da regiao critica 27c
  	direita();
  	//cruzamento4
  	try {
	    Semaforos.cruzamento4.acquire();
	  	direita();
	  	direita();
	  	Semaforos.cruzamento4.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento4
  	direita();
  	//canto superior direito
  	direita();
  	baixo();
  	//fim do canto superior direito
  	baixo();
    //regiao critica rua28d
    try {
      Semaforos.rua28d.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento10
  	try {
	    Semaforos.cruzamento10.acquire();
	  	baixo();
	  	baixo();
	  	Semaforos.cruzamento10.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento10
  	baixo();
    //regiao critica rua27d
    try {
      Semaforos.rua27d.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento16
  	try {
	    Semaforos.cruzamento16.acquire();
	  	baixo();
	  	baixo();
	  	Semaforos.cruzamento16.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento16
  	baixo();
    //regiao critica 12d
    //regiao critica 23d
    try {
      Semaforos.rua12d.acquire();
      Semaforos.rua23d.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento22
  	try {
	    Semaforos.cruzamento22.acquire();
	  	baixo();
	  	baixo();
	  	Semaforos.cruzamento22.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento22
    Semaforos.rua27d.release();
    //fim da regiao critica 27d
    Semaforos.rua26b.release();
    //fim da regiao critica 26b
  	baixo();
  	//cruzamento28
  	try {
	    Semaforos.cruzamento28.acquire();
	  	baixo();
	  	baixo();
	  	Semaforos.cruzamento28.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento28
    Semaforos.rua28d.release();
    //fim da regiao critica rua28d
  	baixo();
  	//canto inferior direito
  	baixo();
  	esquerda();
  	//fim do canto inferior direito
  	esquerda();
  	//cruzamento32
  	try {
	    Semaforos.cruzamento32.acquire();
	  	esquerda();
	  	cima();
	  	Semaforos.cruzamento32.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento32
    Semaforos.rua23d.release();
    //fim da regiao critica 23d
    Semaforos.rua12d.release();
    //fim da regiao critica 12d
    Semaforos.rua25a.release();
    //fim da regiao critica rua25a
  	cima();
    //regiao critica rua27e
    try {
      Semaforos.rua27e.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento27
  	try {
	    Semaforos.cruzamento27.acquire();
	  	cima();
	  	cima();
	  	Semaforos.cruzamento27.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento27
  	cima();
    //regiao critica 12c
    //regiao critica 23c
    //regiao critica 26c
    try {
      Semaforos.rua12c.acquire();
      Semaforos.rua23c.acquire();
      Semaforos.rua26c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento21
  	try {
	    Semaforos.cruzamento21.acquire();
	  	cima();
	  	esquerda();
	  	Semaforos.cruzamento21.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento21
    Semaforos.rua27e.release();
    //fim da regiao critica 27e
  	esquerda();
    //regiao critica rua25b
    try {
      Semaforos.rua25b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento20
  	try {
	    Semaforos.cruzamento20.acquire();
	  	esquerda();
	  	cima();
	  	Semaforos.cruzamento20.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento20
    Semaforos.rua26c.release();
    //fim da regiao critica 26c
    Semaforos.rua23c.release();
    //fim da regiao critica 23c
    Semaforos.rua12c.release();
    //fim da regiao critica 12c
  	cima();
  	//cruzamento14
  	try {
	    Semaforos.cruzamento14.acquire();
	  	cima();
	  	esquerda();
	  	Semaforos.cruzamento14.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento14
    Semaforos.rua25b.release();
    //fim da regiao critica 25b
  	esquerda();
    //regiao critica 12b
    //regiao critica 24b
    //regiao critica 26d
    //regiao critica 23b
    try {
      Semaforos.rua23b.acquire(); //solucao para o primeiro deadlock chamar o semaforo antes
      Semaforos.rua12b.acquire();
      Semaforos.rua24b.acquire();
      Semaforos.rua26d.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento13
  	try {
	    Semaforos.cruzamento13.acquire();
	  	esquerda();
	  	baixo();
	  	Semaforos.cruzamento13.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento13
  	baixo();
    /*
    //regiao critica 23b
    try {
      Semaforos.rua23b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    */
  	//cruzamento19
  	try {
	    Semaforos.cruzamento19.acquire();
	  	baixo();
	  	esquerda();
	  	Semaforos.cruzamento19.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento19
    Semaforos.rua26d.release();
    //fim da regiao critica 26d
    Semaforos.rua24b.release();
    //fim da regiao critica 24b
    Semaforos.rua12b.release();
    //fim da regiao critica 12b
  	esquerda();
    //regiao critica rua27f
    try {
      Semaforos.rua27f.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento18
  	try {
	    Semaforos.cruzamento18.acquire();
	  	esquerda();
	  	baixo();
	  	Semaforos.cruzamento18.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento18
    Semaforos.rua23b.release();
    //fim da regiao critica 23b
  	baixo();
  	//cruzamento24
  	try {
	    Semaforos.cruzamento24.acquire();
	  	baixo();
	  	baixo();
	  	Semaforos.cruzamento24.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento24
    Semaforos.rua27f.release();
    //fim da regiao critica 27f
  }

  /* ***************************************************************
  * Metodo: percursoCarro3.
  * Funcao: faz o carro3 percorrer o seu percurso sem haver colisao com outro carro.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void percursoCarro3(){
  	esquerda();
    //regiao critica rua23a
    try {
      Semaforos.rua23a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento29
  	try {
	    Semaforos.cruzamento29.acquire();
	  	esquerda();
	  	esquerda();
	  	Semaforos.cruzamento29.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento29
  	esquerda();
  	//canto inferior esquerdo
  	esquerda();
  	cima();
  	//fim do canto inferior esquerdo
  	cima();
    //regiao critica rua38a
    try {
      Semaforos.rua38a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento23
  	try {
	    Semaforos.cruzamento23.acquire();
	  	cima();
	  	cima();
	  	Semaforos.cruzamento23.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento23
  	cima();
    //regiao critica rua37a
    try {
      Semaforos.rua37a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento17
  	try {
	    Semaforos.cruzamento17.acquire();
	  	cima();
	  	direita();
	  	Semaforos.cruzamento17.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento17
    Semaforos.rua38a.release();
    //fim da regiao critica 38a
    Semaforos.rua13a.release();
    //fim da regiao critica rua13a
    Semaforos.rua23a.release();
    //fim da regiao critica rua23a
    Semaforos.rua34a.release();
    //fim da regiao critica rua34a
  	direita();
    //regiao critica rua23b
    try {
      Semaforos.rua23b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento18
  	try {
	    Semaforos.cruzamento18.acquire();
	  	direita();
	  	direita();
	  	Semaforos.cruzamento18.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento18
    Semaforos.rua37a.release();
    //fim da regiao critica 37a
  	direita();
    //regiao critica 13a
    //regiao critica 36a
    try {
      Semaforos.rua13a.acquire();
      Semaforos.rua36a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento19
  	try {
	    Semaforos.cruzamento19.acquire();
	  	direita();
	  	direita();
	  	Semaforos.cruzamento19.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento19
    Semaforos.rua23b.release();
    //fim da rua 23b
  	direita();
    //regiao critica rua23c
    try {
      Semaforos.rua23c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento20
  	try {
	    Semaforos.cruzamento20.acquire();
	  	direita();
	  	direita();
	  	Semaforos.cruzamento20.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento20
  	direita();
    //regiao critica rua37b
    try {
      Semaforos.rua37b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento21
  	try {
	    Semaforos.cruzamento21.acquire();
	  	direita();
	  	direita();
	  	Semaforos.cruzamento21.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento21
    Semaforos.rua23c.release();
    //fim da regiao critica 23c
  	direita();
    //regiao critica rua35a
    //regiao critica rua23d
    //regiao critica rua38b
    try {
      Semaforos.rua35a.acquire();
      Semaforos.rua23d.acquire();
      Semaforos.rua38b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento22
  	try {
	    Semaforos.cruzamento22.acquire();
	  	direita();
	  	baixo();
	  	Semaforos.cruzamento22.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento22
    Semaforos.rua37b.release();
    //fim da regiao critica 37b
    Semaforos.rua36a.release();
    //fim da regiao critica 36a
  	baixo();
  	//cruzamento28
  	try {
	    Semaforos.cruzamento28.acquire();
	  	baixo();
	  	baixo();
	  	Semaforos.cruzamento28.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento28
    Semaforos.rua38b.release();
    //fim da regiao critica 38b
  	baixo();
  	//canto inferior direito
  	baixo();
  	esquerda();
  	//fim do canto inferior direito
  	esquerda();
  	//cruzamento32
  	try {
	    Semaforos.cruzamento32.acquire();
	  	esquerda();
	  	esquerda();
	  	Semaforos.cruzamento32.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento32
    Semaforos.rua23d.release();
    //fim da regiao critica rua23d
  	esquerda();
    //regiao critica rua37c
    //regiao critica rua38c
    try {
      Semaforos.rua37c.acquire();
      Semaforos.rua38c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento31
  	try {
	    Semaforos.cruzamento31.acquire();
	  	esquerda();
	  	esquerda();
	  	Semaforos.cruzamento31.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento31
    Semaforos.rua35a.release();
    //fim da regiao critica 35a
  	esquerda();
    //regiao critica rua34a
    try {
      Semaforos.rua34a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//cruzamento30
  	try {
	    Semaforos.cruzamento30.acquire();
	  	esquerda();
	  	esquerda();
	  	Semaforos.cruzamento30.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
  	//fim do cruzamento30
    Semaforos.rua38c.release();
    //fim da regiao critica 38c
    Semaforos.rua37c.release();
    //fim da regiao critica 37c
  }


  /* ***************************************************************
  * Metodo: percursoCarro4.
  * Funcao: faz o carro4 percorrer o seu percurso sem haver colisao com outro carro.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void percursoCarro4(){
  	baixo();
    //regiao critica rua47c
    //regiao critica rua48c
    try {
      Semaforos.rua47c.acquire();
      Semaforos.rua48c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento25
    try {
      Semaforos.cruzamento25.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento25.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento25
  	baixo();
    //regiao critica 14a
    //regiao critica 34a
    try {
      Semaforos.rua14a.acquire();
      Semaforos.rua34a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento30
    try {
      Semaforos.cruzamento30.acquire();
      baixo();
      esquerda();
      Semaforos.cruzamento30.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento30
    Semaforos.rua48c.release();
    //fim da regiao critica 48c
    Semaforos.rua47c.release();
    //fim da regiao critica 47c
  	esquerda();
    //regiao critica rua24a
    try {
      Semaforos.rua24a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento29
    try {
      Semaforos.cruzamento29.acquire();
      esquerda();
      esquerda();
      Semaforos.cruzamento29.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento29
  	esquerda();
    //canto inferior esquerdo
    esquerda();
    cima();
    //fim do canto inferior esquerdo
  	cima();
    //regiao critica rua48a
    try {
      Semaforos.rua48a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento23
    try {
      Semaforos.cruzamento23.acquire();
      cima();
      cima();
      Semaforos.cruzamento23.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento23
  	cima();
    //regiao critica rua47a
    try {
      Semaforos.rua47a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento17
    try {
      Semaforos.cruzamento17.acquire();
      cima();
      cima();
      Semaforos.cruzamento17.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento17
    Semaforos.rua34a.release();
    //fim da regiao critica 34a
  	cima();
    //cruzamento11
    try {
      Semaforos.cruzamento11.acquire();
      cima();
      cima();
      Semaforos.cruzamento11.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento11
    Semaforos.rua47a.release();
    //fim da regiao critica 47a
  	cima();
    //cruzamento5
    try {
      Semaforos.cruzamento5.acquire();
      cima();
      cima();
      Semaforos.cruzamento5.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento5
    Semaforos.rua48a.release();
    //fim da regiao critica 48a
  	cima();
    //canto superior esquerdo
    cima();
    direita();
    //fim do canto superior esquerdo
  	direita();
    //cruzamento1
    try {
      Semaforos.cruzamento1.acquire();
      direita();
      direita();
      Semaforos.cruzamento1.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento1
  	direita();
    //regiao critica rua46a
    //regiao critica rua47b
    //regiao critica rua48b
    try {
      Semaforos.rua46a.acquire();
      Semaforos.rua47b.acquire();
      Semaforos.rua48b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento2
    try {
      Semaforos.cruzamento2.acquire();
      direita();
      baixo();
      Semaforos.cruzamento2.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento2
  	baixo();
    //cruzamento7
    try {
      Semaforos.cruzamento7.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento7.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento7
    Semaforos.rua48b.release();
    //fim da regiao critica 48b
    Semaforos.rua47b.release();
    //fim da regiao critica 47b
    Semaforos.rua24a.release();
    //fim da regiao critica 24a
  	baixo();
    //regiao critica rua24b
    try {
      Semaforos.rua24b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento13
    try {
      Semaforos.cruzamento13.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento13.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento13
  	baixo();
    //cruzamento19
    try {
      Semaforos.cruzamento19.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento19.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento19
    Semaforos.rua24b.release();
    //fim da regiao critica 24b
    Semaforos.rua46a.release();
    //fim da regiao critica 46a
    Semaforos.rua14a.release();
    //fim da regiao critica 14a
  }

  /* ***************************************************************
  * Metodo: percursoCarro5.
  * Funcao: faz o carro5 percorrer o seu percurso sem haver colisao com outro carro.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void percursoCarro5(){
    baixo();
    //regiao critica rua56a
    //regiao critica rua15a
    //regiao critica rua57c
    //regiao critica rua58c
    try {
      Semaforos.rua56a.acquire(); //mudanca para resolver o terceiro deadlock
      Semaforos.rua15a.acquire(); //mudanca para resolver o quarto deadlock
      Semaforos.rua57c.acquire();
      Semaforos.rua58c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento26
    try {
      Semaforos.cruzamento26.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento26.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento26
    baixo();
    //regiao critica rua15a
    //regiao critica rua35a
    try {
      //Semaforos.rua15a.acquire();
      Semaforos.rua35a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento31
    try {
      Semaforos.cruzamento31.acquire();
      baixo();
      direita();
      Semaforos.cruzamento31.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento31
    Semaforos.rua57c.release();
    //fim da regiao critica 57c
    Semaforos.rua58c.release();
    //fim da regiao critica 58c
    direita();
    //regiao critica rua25a
    try {
      Semaforos.rua25a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento32
    try {
      Semaforos.cruzamento32.acquire();
      direita();
      direita();
      Semaforos.cruzamento32.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento32
    direita();
    //canto inferior direito
      direita();
      cima();
    //fim do canto inferior direito
    cima();
    //regiao critica rua58b
    try {
      Semaforos.rua58b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento28
    try {
      Semaforos.cruzamento28.acquire();
      cima();
      cima();
      Semaforos.cruzamento28.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento28
    cima();
    //regiao critica rua56a
    //regiao critica rua57b
    try {
      //Semaforos.rua56a.acquire(); alteracao para resolver o deadlock
      Semaforos.rua57b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento22
    try {
      Semaforos.cruzamento22.acquire();
      cima();
      cima();
      Semaforos.cruzamento22.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento22
    Semaforos.rua35a.release();
    //fim da regiao critica 35a
    Semaforos.rua15a.release();
    //fim da regiao critica 15a
    cima();
    //cruzamento16
    try {
      Semaforos.cruzamento16.acquire();
      cima();
      cima();
      Semaforos.cruzamento16.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento16
    Semaforos.rua57b.release();
    //fim da regiao critica 57b
    //
    cima();
    //cruzamento10
    try {
      Semaforos.cruzamento10.acquire();
      cima();
      cima();
      Semaforos.cruzamento10.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento10
    Semaforos.rua58b.release();
    //fim da regiao critica 58b
    cima();
    //canto superior direito
      cima();
      esquerda();
    //fim do canto superior direito
    esquerda();
    //cruzamento4
    try {
      Semaforos.cruzamento4.acquire();
      esquerda();
      esquerda();
      Semaforos.cruzamento4.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento4
    esquerda();
    //regiao critica rua57a
    //regiao critica rua58a
    try {
      Semaforos.rua57a.acquire();
      Semaforos.rua58a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento3
    try {
      Semaforos.cruzamento3.acquire();
      esquerda();
      baixo();
      Semaforos.cruzamento3.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento3
    Semaforos.rua56a.release();
    //fim da regiao critica 56a
    baixo();
    //cruzamento8
    try {
      Semaforos.cruzamento8.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento8.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento8
    Semaforos.rua58a.release();
    //fim da regiao critica 58a
    Semaforos.rua57a.release();
    //fim da regiao critica 57a
    Semaforos.rua25a.release();
    //fim da regiao critica 25a
    baixo();
    //regiao critica rua25b
    try {
      Semaforos.rua25b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento14
    try {
      Semaforos.cruzamento14.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento14.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento14
    baixo();
    //cruzamento20
    try {
      Semaforos.cruzamento20.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento20.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento20
    Semaforos.rua25b.release();
    //fim da regiao critica 25b
  }

  /* ***************************************************************
  * Metodo: percursoCarro6.
  * Funcao: faz o carro6 percorrer o seu percurso sem haver colisao com outro carro.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void percursoCarro6(){
  	baixo();
    //reigao critica 68b
    try {
      Semaforos.rua68b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento10
    try {
      Semaforos.cruzamento10.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento10.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento10
  	baixo();
    //reigao critica 67b
    try {
      Semaforos.rua67b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento16
    try {
      Semaforos.cruzamento16.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento16.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento16
  	baixo();
    //regiao critica 16a
    //reigao critica 36a
    try {
      Semaforos.rua16a.acquire();
      Semaforos.rua36a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento22
    try {
      Semaforos.cruzamento22.acquire();
      baixo();
      esquerda();
      Semaforos.cruzamento22.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento22
    Semaforos.rua68b.release();
    //fim da regiao critica 68b
    Semaforos.rua56a.release();
    //fim da regiao critica 56a
    Semaforos.rua26b.release();
    //fim da regiao critica 26b
  	esquerda();
    //regiao critica rua26c
    try {
      Semaforos.rua26c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento21
    try {
      Semaforos.cruzamento21.acquire();
      esquerda();
      esquerda();
      Semaforos.cruzamento21.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento21
    Semaforos.rua67b.release();
    //fim da regiao critica 67b
  	esquerda();
    //cruzamento20
    try {
      Semaforos.cruzamento20.acquire();
      esquerda();
      esquerda();
      Semaforos.cruzamento20.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento20
    Semaforos.rua26c.release();
    //fim da regiao critica 26c
  	esquerda();
    //regiao critica rua26d
    //regiao critica rua46a
    try {
      Semaforos.rua26d.acquire();
      Semaforos.rua46a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento19
    try {
      Semaforos.cruzamento19.acquire();
      esquerda();
      cima();
      Semaforos.cruzamento19.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento19
    Semaforos.rua36a.release();
    //fim da regiao critica 36a
  	cima();
    //cruzamento13
    try {
      Semaforos.cruzamento13.acquire();
      cima();
      cima();
      Semaforos.cruzamento13.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento13
    Semaforos.rua26d.release();
    //fim da regiao critica 26d
  	cima();
    //regiao critica rua26a
    //regiao critica rua67a
    //regiao critica rua68a
    try {
      Semaforos.rua26a.acquire();
      Semaforos.rua67a.acquire();
      Semaforos.rua68a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento7
    try {
      Semaforos.cruzamento7.acquire();
      cima();
      cima();
      Semaforos.cruzamento7.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento7
  	cima();
    //cruzamento2
    try {
      Semaforos.cruzamento2.acquire();
      cima();
      direita();
      Semaforos.cruzamento2.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento2
    Semaforos.rua26a.release();
    //fim da regiao critica 26a
    Semaforos.rua46a.release();
    //fim da regiao critica 46a
    Semaforos.rua16a.release();
    //fim da regiao critica 16a
  	direita();
    //regiao critica rua26b
    //regiao critica rua56a
    try {
      Semaforos.rua26b.acquire();
      Semaforos.rua56a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento3
    try {
      Semaforos.cruzamento3.acquire();
      direita();
      direita();
      Semaforos.cruzamento3.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento3
    Semaforos.rua68a.release();
    //fim da regiao critica 68a
    Semaforos.rua67a.release();
    //fim da regiao critica 67a
  	direita();
    //cruzamento4
    try {
      Semaforos.cruzamento4.acquire();
      direita();
      direita();
      Semaforos.cruzamento4.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento4
  	direita();
    //canto superior direito
    direita();
    baixo();
    //fim do canto superior direito
  }

  /* ***************************************************************
  * Metodo: percursoCarro7.
  * Funcao: faz o carro7 percorrer o seu percurso sem haver colisao com outro carro.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void percursoCarro7(){
  	esquerda();
    //regiao critica 17d
    //regiao critica 27a
    //regiao critica 47a
    //regiao critica 78a
    try {
      Semaforos.rua17d.acquire();
      Semaforos.rua27a.acquire();
      Semaforos.rua47a.acquire();
      Semaforos.rua78a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento11
    try {
      Semaforos.cruzamento11.acquire();
      esquerda();
      baixo();
      Semaforos.cruzamento11.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento11
  	baixo();
    //regiao critica rua37a
    try {
      Semaforos.rua37a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento17
    try {
      Semaforos.cruzamento17.acquire();
      baixo();
      direita();
      Semaforos.cruzamento17.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento17
    Semaforos.rua78a.release();
    //fim da regiao critica 78a
    Semaforos.rua47a.release();
    //fim da regiao critica 47a
    Semaforos.rua27a.release();
    //fim da regiao critica 27a
    Semaforos.rua17d.release();
    //fim da regiao critica 17d
  	direita();
    //regiao critica rua27f
    try {
      Semaforos.rua27f.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento18
    try {
      Semaforos.cruzamento18.acquire();
      direita();
      baixo();
      Semaforos.cruzamento18.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento18
    Semaforos.rua37a.release();
    //fim da regiao critica 37a
  	baixo();
    //regiao critica rua78d
    try {
      Semaforos.rua78d.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento24
    try {
      Semaforos.cruzamento24.acquire();
      baixo();
      direita();
      Semaforos.cruzamento24.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento24
    Semaforos.rua27f.release();
    //fim da regiao critica 27f
  	direita();
    //regiao critica rua47c
    try {
      Semaforos.rua47c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento25
    try {
      Semaforos.cruzamento25.acquire();
      direita();
      baixo();
      Semaforos.cruzamento25.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento25
  	baixo();
    //regiao critica 17c
    //regiao critica 37c
    try {
      Semaforos.rua17c.acquire();
      Semaforos.rua37c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento30
    try {
      Semaforos.cruzamento30.acquire();
      baixo();
      direita();
      Semaforos.cruzamento30.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento30
    Semaforos.rua47c.release();
    //fim da regiao critica 47c
  	direita();
    //reigao critica 57c
    try {
      Semaforos.rua57c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento31
    try {
      Semaforos.cruzamento31.acquire();
      direita();
      cima();
      Semaforos.cruzamento31.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento31
    Semaforos.rua37c.release();
    //fim da regiao critica 37c
    Semaforos.rua17c.release();
    //fim da regiao 17c
  	cima();
    //cruzamento26
    try {
      Semaforos.cruzamento26.acquire();
      cima();
      direita();
      Semaforos.cruzamento26.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento26
    Semaforos.rua57c.release();
    //fim da regiao critica 57c
  	direita();
    //regiao critica rua27e
    try {
      Semaforos.rua27e.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento27
    try {
      Semaforos.cruzamento27.acquire();
      direita();
      cima();
      Semaforos.cruzamento27.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento27
    Semaforos.rua78d.release();
    //fim da regiao crtiica 78d
  	cima();
    //regiao critica 17b
    //regiao critica 37b
    //reigao critica 67b
    try {
      Semaforos.rua67b.acquire();
      Semaforos.rua17b.acquire();
      Semaforos.rua37b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento21
    try {
      Semaforos.cruzamento21.acquire();
      cima();
      direita();
      Semaforos.cruzamento21.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento21
    Semaforos.rua27e.release();
    //fim da regiao critica 27e
  	direita();
    //regiao critica rua27d
    //regiao critica rua57b
    //regiao critica rua78c
    try {
      Semaforos.rua27d.acquire();
      Semaforos.rua57b.acquire();
      Semaforos.rua78c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento22
    try {
      Semaforos.cruzamento22.acquire();
      direita();
      cima();
      Semaforos.cruzamento22.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento22
    Semaforos.rua37b.release();
    //fima da regiao critica 37b
    Semaforos.rua17b.release();
    //fim da regiao 17b
  	cima();
    //cruzamento16
    try {
      Semaforos.cruzamento16.acquire();
      cima();
      esquerda();
      Semaforos.cruzamento16.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento16
    Semaforos.rua78c.release();
    //fim da regiao critica 78c
    Semaforos.rua57b.release();
    //fim da regiao critica 57b
    Semaforos.rua27d.release();
    //fim da regiao critica 27d
    Semaforos.rua67b.release();
    //fim da regiao critica 67b
  	esquerda();
    //cruzamento15
    try {
      Semaforos.cruzamento15.acquire();
      esquerda();
      cima();
      Semaforos.cruzamento15.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento15
  	cima();
    //regiao critica rua78b
    //regiao critica rua67a
    try {
      Semaforos.rua78b.acquire();
      Semaforos.rua67a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento9
    try {
      Semaforos.cruzamento9.acquire();
      cima();
      esquerda();
      Semaforos.cruzamento9.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento9
  	esquerda();
    //regiao critica rua27c
    //regiao critica rua57a
    try {
      Semaforos.rua27c.acquire();
      Semaforos.rua57a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento8
    try {
      Semaforos.cruzamento8.acquire();
      esquerda();
      cima();
      Semaforos.cruzamento8.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento8
  	cima();
    /*
    //reigao critica 67a
    try {
      Semaforos.rua67a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }*/
    //cruzamento3
    try {
      Semaforos.cruzamento3.acquire();
      cima();
      esquerda();
      Semaforos.cruzamento3.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento3
    Semaforos.rua57a.release();
    //fim da regiao critica 57a
    Semaforos.rua27c.release();
    //fim da regiao critica 27c
  	esquerda();
    //regiao critica 17a
    //regiao critica 27b
    //regiao critica 47b
    try {
      Semaforos.rua17a.acquire();
      Semaforos.rua27b.acquire();
      Semaforos.rua47b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento2
    try {
      Semaforos.cruzamento2.acquire();
      esquerda();
      baixo();
      Semaforos.cruzamento2.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento2
  	baixo();
    //cruzamento7
    try {
      Semaforos.cruzamento7.acquire();
      baixo();
      esquerda();
      Semaforos.cruzamento7.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento7
    Semaforos.rua47b.release();
    //fim da regiao critica 47b
    Semaforos.rua27b.release();
    //fim da regiao critica 27b
    Semaforos.rua17a.release();
    //fim da regiao critica 17a
    Semaforos.rua67a.release();
    //fim da regiao critica 67a
  	esquerda();
    //cruzamento6
    try {
      Semaforos.cruzamento6.acquire();
      esquerda();
      baixo();
      Semaforos.cruzamento6.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento6
    Semaforos.rua78b.release();
    //fim da regiao critica 78b
  	baixo();
    //cruzamento12
    try {
      Semaforos.cruzamento12.acquire();
      baixo();
      esquerda();
      Semaforos.cruzamento12.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento12
  }

  /* ***************************************************************
  * Metodo: percursoCarro8.
  * Funcao: faz o carro8 percorrer o seu percurso sem haver colisao com outro carro.
  * Parametros: nenhum.
  * Retorno: sem retorno.
  *************************************************************** */
  public void percursoCarro8(){
  	direita();
    //regiao critica rua78b
    try {
      Semaforos.rua78b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento6
    try {
      Semaforos.cruzamento6.acquire();
      direita();
      direita();
      Semaforos.cruzamento6.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento6
  	direita();
    //regiao critica 18a
    //regiao critica 28b
    //regiao critica 48b
    //regiao critica 68a
    try {
      Semaforos.rua28b.acquire();
      Semaforos.rua68a.acquire();
      Semaforos.rua48b.acquire();
      Semaforos.rua18a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento7
    try {
      Semaforos.cruzamento7.acquire();
      direita();
      cima();
      Semaforos.cruzamento7.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento7
  	cima();
    //cruzamento2
    try {
      Semaforos.cruzamento2.acquire();
      cima();
      direita();
      Semaforos.cruzamento2.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento2
    Semaforos.rua48b.release();
    //fim da regiao crtitica 48b
    Semaforos.rua28b.release();
    //fim da regiao critica 28b
    Semaforos.rua18a.release();
    //fim da regiao critica 18a
  	direita();
    //regiao critica rua28c
    //regiao crtiica rua58a
    try {
      Semaforos.rua28c.acquire();
      Semaforos.rua58a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento3
    try {
      Semaforos.cruzamento3.acquire();
      direita();
      baixo();
      Semaforos.cruzamento3.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento3
    Semaforos.rua68a.release();
    //fim da regiao critica 68a
  	baixo();
    //cruzamento8
    try {
      Semaforos.cruzamento8.acquire();
      baixo();
      direita();
      Semaforos.cruzamento8.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento8
    Semaforos.rua58a.release();
    //fim da regiao critica 58a
    Semaforos.rua28c.release();
    //fim da regiao critica 28c
  	direita();
    //cruzamento9
    try {
      Semaforos.cruzamento9.acquire();
      direita();
      direita();
      Semaforos.cruzamento9.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento9
    Semaforos.rua78b.release();
    //fim da regiao critica 78b
  	direita();
    //regiao critica rua28d
    //regiao critica rua58b
    //regiao crtiica rua68b
    try {
      Semaforos.rua28d.acquire();
      Semaforos.rua58b.acquire();
      Semaforos.rua68b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento10
    try {
      Semaforos.cruzamento10.acquire();
      direita();
      baixo();
      Semaforos.cruzamento10.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento10
  	baixo();
    //regiao critica rua78c
    try {
      Semaforos.rua78c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento16
    try {
      Semaforos.cruzamento16.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento16.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento16
  	baixo();
    //regiao critica 18b
    //regiao critica 38b
    try {
      Semaforos.rua18b.acquire();
      Semaforos.rua38b.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento22
    try {
      Semaforos.cruzamento22.acquire();
      baixo();
      baixo();
      Semaforos.cruzamento22.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento22
    Semaforos.rua78c.release();
    //fim da regiao critica 78c
    Semaforos.rua68b.release();
    //fim da regiao critica 68b
  	baixo();
    //cruzamento28
    try {
      Semaforos.cruzamento28.acquire();
      baixo();
      esquerda();
      Semaforos.cruzamento28.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento28
    Semaforos.rua38b.release();
    //fim da regiao critica 38b
    Semaforos.rua18b.release();
    //fim da regiao critica 18b
    Semaforos.rua58b.release();
    //fim da regiao critica 58b
    Semaforos.rua28d.release();
    //fim da regiao critica 28d
  	esquerda();
    //regiao critica rua78d
    //regiao critica rua48c
    try {
      Semaforos.rua78d.acquire();
      Semaforos.rua48c.acquire(); //solucao para o segundo deadlock é chamar o semaforo da rua48c nesse ponto
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento27
    try {
      Semaforos.cruzamento27.acquire();
      esquerda();
      esquerda();
      Semaforos.cruzamento27.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento27
  	esquerda();
    //reigao critica 58c
    try {
      Semaforos.rua58c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento26
    try {
      Semaforos.cruzamento26.acquire();
      esquerda();
      baixo();
      Semaforos.cruzamento26.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento26
  	baixo();
    //regiao critica 18c
    //regiao critica 38c
    try {
      Semaforos.rua18c.acquire();
      Semaforos.rua38c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento31
    try {
      Semaforos.cruzamento31.acquire();
      baixo();
      esquerda();
      Semaforos.cruzamento31.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento31
    Semaforos.rua58c.release();
    //fim da regiao critica 58c
  	esquerda();
    /*
    //regiao critica rua48c
    try {
      Semaforos.rua48c.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    */
    //cruzamento30
    try {
      Semaforos.cruzamento30.acquire();
      esquerda();
      cima();
      Semaforos.cruzamento30.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento30
    Semaforos.rua38c.release();
    //fim da regiao critica 38c
    Semaforos.rua18c.release();
    //fim da regiao critica 18c
  	cima();
    //cruzamento25
    try {
      Semaforos.cruzamento25.acquire();
      cima();
      esquerda();
      Semaforos.cruzamento25.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento25
    Semaforos.rua48c.release();
    //fim da regiao critica 48c
  	esquerda();
    //cruzamento24
    try {
      Semaforos.cruzamento24.acquire();
      esquerda();
      esquerda();
      Semaforos.cruzamento24.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento24
    Semaforos.rua78d.release();
    //fim da regiao critica 78d
  	esquerda();
    //regiao critica 18d
    //regiao critica 28a
    //regiao critica 38a
    //regiao critica 48a
    try {
      Semaforos.rua18d.acquire();
      Semaforos.rua28a.acquire();
      Semaforos.rua38a.acquire();
      Semaforos.rua48a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento23
    try {
      Semaforos.cruzamento23.acquire();
      esquerda();
      cima();
      Semaforos.cruzamento23.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento23
  	cima();
    //regiao critica rua78a
    try {
      Semaforos.rua78a.acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //cruzamento17
    try {
      Semaforos.cruzamento17.acquire();
      cima();
      cima();
      Semaforos.cruzamento17.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento17
    Semaforos.rua38a.release();
    //fim da regiao critica 38a
  	cima();
    //cruzamento11
    try {
      Semaforos.cruzamento11.acquire();
      cima();
      cima();
      Semaforos.cruzamento11.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento11
    Semaforos.rua78a.release();
    //fim da regiao critica 78a
  	cima();
    //cruzamento5
    try {
      Semaforos.cruzamento5.acquire();
      cima();
      direita();
      Semaforos.cruzamento5.release();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restaura o status de interrupcao
    }
    //fim do cruzamento5c
    Semaforos.rua48a.release();
    //fim da reigao critica 48a
    Semaforos.rua28a.release();
    //fim da regiao critica 28a
    Semaforos.rua18d.release();
    //fim da regiao critica 18d
  }

}