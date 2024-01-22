/* ***************************************************************
* Autor............: Hugo Botelho Santana
* Matricula........: 202210485
* Inicio...........: 20/11/2023
* Ultima alteracao.: 04/12/2023
* Nome.............: Simulacao de Transito/Trafego Automato
* Funcao...........: Simular um sistema de transito automoto
*************************************************************** */

import java.util.concurrent.Semaphore;

public class Semaforos{
  //Semaforos de cruzamentos
  public static Semaphore cruzamento1 = new Semaphore(1);
  public static Semaphore cruzamento2 = new Semaphore(1);
  public static Semaphore cruzamento3 = new Semaphore(1);
  public static Semaphore cruzamento4 = new Semaphore(1);
  public static Semaphore cruzamento5 = new Semaphore(1);
  public static Semaphore cruzamento6 = new Semaphore(1);
  public static Semaphore cruzamento7 = new Semaphore(1);
  public static Semaphore cruzamento8 = new Semaphore(1);
  public static Semaphore cruzamento9 = new Semaphore(1);
  public static Semaphore cruzamento10 = new Semaphore(1);
  public static Semaphore cruzamento11 = new Semaphore(1);
  public static Semaphore cruzamento12 = new Semaphore(1);
  public static Semaphore cruzamento13 = new Semaphore(1);
  public static Semaphore cruzamento14 = new Semaphore(1);
  public static Semaphore cruzamento15 = new Semaphore(1);
  public static Semaphore cruzamento16 = new Semaphore(1);
  public static Semaphore cruzamento17 = new Semaphore(1);
  public static Semaphore cruzamento18 = new Semaphore(1);
  public static Semaphore cruzamento19 = new Semaphore(1);
  public static Semaphore cruzamento20 = new Semaphore(1);
  public static Semaphore cruzamento21 = new Semaphore(1);
  public static Semaphore cruzamento22 = new Semaphore(1);
  public static Semaphore cruzamento23 = new Semaphore(1);
  public static Semaphore cruzamento24 = new Semaphore(1);
  public static Semaphore cruzamento25 = new Semaphore(1);
  public static Semaphore cruzamento26 = new Semaphore(1);
  public static Semaphore cruzamento27 = new Semaphore(1);
  public static Semaphore cruzamento28 = new Semaphore(1);
  public static Semaphore cruzamento29 = new Semaphore(1);
  public static Semaphore cruzamento30 = new Semaphore(1);
  public static Semaphore cruzamento31 = new Semaphore(1);
  public static Semaphore cruzamento32 = new Semaphore(1);

  //Semaforos de ruas
  public static Semaphore rua12a = new Semaphore(0); //inicia com 0 pois o carro 1 ja comeca na regiao critica
  public static Semaphore rua12b = new Semaphore(1);
  public static Semaphore rua12c = new Semaphore(1);
  public static Semaphore rua12d = new Semaphore(1);
  public static Semaphore rua13a = new Semaphore(0); //Inicia com 0 pois o carro 3 ja comeca na reigao critica
  public static Semaphore rua14a = new Semaphore(0); //inicia com 0 pois o carro 1 ja comeca na regiao critica
  public static Semaphore rua15a = new Semaphore(1);
  public static Semaphore rua16a = new Semaphore(1);
  public static Semaphore rua17a = new Semaphore(1);
  public static Semaphore rua17b = new Semaphore(1);
  public static Semaphore rua17c = new Semaphore(1);
  public static Semaphore rua17d = new Semaphore(1);
  public static Semaphore rua18a = new Semaphore(1);
  public static Semaphore rua18b = new Semaphore(1);
  public static Semaphore rua18c = new Semaphore(1);
  public static Semaphore rua18d = new Semaphore(1);

  public static Semaphore rua23a = new Semaphore(1);
  public static Semaphore rua23b = new Semaphore(1);
  public static Semaphore rua23c = new Semaphore(1);
  public static Semaphore rua23d = new Semaphore(1);
  public static Semaphore rua24a = new Semaphore(1);
  public static Semaphore rua24b = new Semaphore(1);
  public static Semaphore rua25a = new Semaphore(1);
  public static Semaphore rua25b = new Semaphore(1);
  public static Semaphore rua26a = new Semaphore(1);
  public static Semaphore rua26b = new Semaphore(0); //inicia com zero pois o carro 6 ja comeca na regiao critica
  public static Semaphore rua26c = new Semaphore(1);
  public static Semaphore rua26d = new Semaphore(1);
  public static Semaphore rua27a = new Semaphore(1);
  public static Semaphore rua27b = new Semaphore(1);
  public static Semaphore rua27c = new Semaphore(1);
  public static Semaphore rua27d = new Semaphore(1);
  public static Semaphore rua27e = new Semaphore(1);
  public static Semaphore rua27f = new Semaphore(1);
  public static Semaphore rua28a = new Semaphore(1);
  public static Semaphore rua28b = new Semaphore(1);
  public static Semaphore rua28c = new Semaphore(1);
  public static Semaphore rua28d = new Semaphore(1);

  public static Semaphore rua34a = new Semaphore(0); //inicia com zero pois o carro 3 ja comeca na regiao critica
  public static Semaphore rua35a = new Semaphore(1);
  public static Semaphore rua36a = new Semaphore(1);
  public static Semaphore rua37a = new Semaphore(1);
  public static Semaphore rua37b = new Semaphore(1);
  public static Semaphore rua37c = new Semaphore(1);
  public static Semaphore rua38a = new Semaphore(1);
  public static Semaphore rua38b = new Semaphore(1);
  public static Semaphore rua38c = new Semaphore(1);

  public static Semaphore rua46a = new Semaphore(1);
  public static Semaphore rua47a = new Semaphore(1);
  public static Semaphore rua47b = new Semaphore(1);
  public static Semaphore rua47c = new Semaphore(1);
  public static Semaphore rua48a = new Semaphore(1);
  public static Semaphore rua48b = new Semaphore(1);
  public static Semaphore rua48c = new Semaphore(1);

  public static Semaphore rua56a = new Semaphore(0); //inicia com zero pois o carro 6 ja comeca na regiao critica
  public static Semaphore rua57a = new Semaphore(1);
  public static Semaphore rua57b = new Semaphore(1);
  public static Semaphore rua57c = new Semaphore(1);
  public static Semaphore rua58a = new Semaphore(1);
  public static Semaphore rua58b = new Semaphore(1);
  public static Semaphore rua58c = new Semaphore(1);

  public static Semaphore rua67a = new Semaphore(1);
  public static Semaphore rua67b = new Semaphore(1);
  public static Semaphore rua68a = new Semaphore(1);
  public static Semaphore rua68b = new Semaphore(1);

  public static Semaphore rua78a = new Semaphore(1);
  public static Semaphore rua78b = new Semaphore(1);
  public static Semaphore rua78c = new Semaphore(1);
  public static Semaphore rua78d = new Semaphore(1);
}