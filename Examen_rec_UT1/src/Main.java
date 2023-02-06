import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String path = "./src/videojuegosobj.obj";
    static ObjectOutputStream objectOutputStream;
    static ObjectInputStream objectInputStream;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String search;
        //int cont = 0; //borrar
        String[] names ={"Fifa 19","Call of Duty","God of War","Hollow Knight","Outer Wilds"};
        String[] plat = {"PS4","PC","PS5","XBOX","PC"};
        Double[] price = {59.99,49.99,79.99,14.49,19.99};
        RandomAccessIO randomAccessIO = new RandomAccessIO("./src/videojuegosdat.dat");

        ArrayList<VideoJuego> videoArray = new ArrayList<>();
        RandomAccessIO.BYTES[] bytes = new RandomAccessIO.BYTES[]{
            RandomAccessIO.BYTES.STRINGBUFFER,
            RandomAccessIO.BYTES.STRINGBUFFER,
            RandomAccessIO.BYTES.DOUBLE
        };

        //Escribimos los objetos en el fichero aleatorio
        randomAccessIO.initWriter();
        for(int i =0; i<names.length; i++){
            randomAccessIO.writeFile(RandomAccessIO.BYTES.STRINGBUFFER,names[i]);
            randomAccessIO.writeFile(RandomAccessIO.BYTES.STRINGBUFFER,plat[i]);
            randomAccessIO.writeFile(RandomAccessIO.BYTES.DOUBLE,price[i]);
        }
        randomAccessIO.closeWriter();
        //Leemos el fichero
        leerArr( randomAccessIO.readAllFile(bytes), "ANTES");

        System.out.println("Que videojuego está de descuento????");
        search = sc.nextLine();
        spacer();
        int[] idPos = randomAccessIO.searchPositionById(String.valueOf(search),bytes);
        ArrayList<String> listByPos = randomAccessIO.readByPosition(idPos[0],bytes);
        //System.out.println("POSICION 2 = "+ listByPos.get(2));
        Double newnum = Double.parseDouble( listByPos.get(2));
        Double res = newnum-(newnum*0.15);
        //res = Double.parseDouble(String.valueOf(Math.round(res))); //intento de redondear (un par mas de decimales no importan jeje)

        idPos = randomAccessIO.searchPositionById(String.valueOf(listByPos.get(2)),bytes);
        randomAccessIO.initWriter();
        randomAccessIO.writeFileByPos(RandomAccessIO.BYTES.DOUBLE,res,idPos[1]);
        randomAccessIO.closeWriter();
        ArrayList<String> vidString = randomAccessIO.readAllFile(bytes);
        leerArr( randomAccessIO.readAllFile(bytes),"DESPUES");

        //Cargamos los objetos en un ArrayList

        for (int i = 0 ; i<vidString.size(); i+=3) {
            String tempNom, tempPlat;
            Double tempPrice;
            tempNom = vidString.get(i);
            tempPlat = vidString.get(i+1);
            tempPrice = Double.parseDouble(vidString.get(i+2));
            //System.out.println("Nombre= "+tempNom+ "   Plataforma = "+tempPlat+"   Precio = "+tempPrice);
            VideoJuego videojogo = new VideoJuego(tempNom,tempPlat,tempPrice );
            videoArray.add(videojogo);
        }
        spacer();

       // for (int i = 0 ; i<names.length; i++) {
         //   VideoJuego videojogo = new VideoJuego(names[i],plat[i],price[i] );
          //  videoArray.add(videojogo);
       // }
        //objectIO.writeObject(videoArray);
        writeObject(videoArray);
        readObject();

    }

    public static void  leerArr(ArrayList<String> list, String extra){
        int cont = 0;
        System.out.println("___________"+extra+"_________");
        for (int i = 0 ; i<list.size(); i++){
            System.out.print(""+list.get(i)+"    ");
            cont++;
            if(cont == 3){
                System.out.println("");
                cont = 0;
            }
        }

    }
    public static void writeObject(ArrayList<VideoJuego> objectList) {
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));

            for (int i = 0;i<objectList.size();i++) {
                //Cambiar según el objeto
                objectOutputStream.writeObject(objectList.get(i));
            }

            objectOutputStream.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static void readObject() {
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(path));

            try {
                while (true) {
                    //Castea al nombre del objeto correspondiente
                    VideoJuego vidtemp = (VideoJuego) objectInputStream.readObject();
                    if (vidtemp.Platform.equalsIgnoreCase("ps4")){
                    System.out.println(vidtemp.toString());
                }}
            }catch (EOFException e) {}

            objectInputStream.close();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void spacer(){
        System.out.println();
        System.out.println("___________________________________");
        System.out.println();
    }
}