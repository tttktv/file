package p;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan=new Scanner(System.in);
        System.out.println("Введите путь к папке, с которой хотите начать перемещение: ");
        String pathname=scan.nextLine();
        File dir = new File(pathname+"\\");
        // если объект представляет каталог
        if (dir.isDirectory()) {
            // получаем все вложенные объекты в каталоге
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) {
                    System.out.println(item.getName() + "  \t folder"+"\t"+item.length()/1024);
                } else {
                    System.out.println(item.getName() + "\t file"+"\t"+item.length()/1024);
                }
            }
        }
        Scanner in = new Scanner(System.in);
        String name, next, nn;
        boolean F = true;
        while (true) {
            System.out.println("1-создать папку, 2-создать файл, 3-удалить файл или папку, 4-переименовать файл, 5-переместить файл, 6-читать, 7-перемещаться, 8-выход");
            nn = in.nextLine();
            switch (nn) {
                case "1":{
                    String dnm;
                    System.out.println("Имя папки:");
                    dnm = in.nextLine();
                    File myDir = new File(pathname+"\\"+dnm);
                    myDir.mkdir();
                    break;
                }
                case "2": {
                    String fnm;
                    System.out.println("Имя файла:");
                    fnm = in.nextLine();
                    File f1 = new File(pathname+"\\"+fnm);
                    if (f1.createNewFile()) {
                        System.out.println("Файл создан");
                    } else System.out.println("Файл уже существует");
                    break;
                }
                case "3":{
                    String dfile;
                    System.out.println("Имя файла или папки:");
                    dfile = in.nextLine();
                    File df = new File(pathname+"\\"+dfile);
                    if(df.delete()){
                        System.out.println("Файл был удален");
                    }else System.out.println("Файл не найден");
                    break;
                }

                case "4":{
                    String oldfile, newfile;
                    System.out.println("Какой файл переименовать?:");
                    oldfile = in.nextLine();
                    System.out.println("Новое имя:");
                    newfile = in.nextLine();
                    File ofile = new File(pathname+"\\"+oldfile);
                    File nfile = new File(pathname+"\\"+newfile);
                    if(ofile.renameTo(nfile)){
                        System.out.println("Файл переименован успешно");
                    }else{
                        System.out.println("Файл не был переименован");
                    }
                    break;
                }

                case "5":{
                    String mfile,npath;
                    System.out.println("Какой файл переместить?:");
                    mfile = in.nextLine();
                    File movefile = new File(pathname+"\\"+mfile);
                    System.out.println("Укажите новый путь:");
                    npath = in.nextLine();
                    File movef = new File(npath+"\\"+mfile);
                    if(movefile.renameTo(movef)){
                        System.out.println("Файл перемещен");
                    }else{
                        System.out.println("Файл не был перемещен");
                    }
                    break;
                }

                case "6": {
                    String k;
                    System.out.println("Имя файла:");
                    k = in.nextLine();
                    Scanner sc = new Scanner(new File(pathname+"\\" + k));
                    while (sc.hasNextLine()) {
                        String str = sc.nextLine();
                        System.out.println(str);
                    }
                    break;
                }
                case "7": {
                    System.out.println("Перейти в:");
                    name = in.nextLine();
           /* if (name.equals("создать файл")) {
                String fnm;
                System.out.println("Имя файла:");
                fnm = in.nextLine();
                File f1 = new File("file.txt");
                if (f1.createNewFile()) {
                    System.out.println("file.txt Файл был создан в корневой директории проекта");
                } else System.out.println("Файл file.txt уже существует в корневой директории проекта");
            }*/

                    File f = new File(dir, name);

                    F=true;
                    while (F) {
                        // File f = new File(dir, name);
                        if (f.isDirectory()) {
                            for (File item : f.listFiles()) {
                                if (item.isDirectory()) {
                                    System.out.println(item.getName() + "  \t folder"+"\t"+item.length()/1024);
                                } else {
                                    System.out.println(item.getName() + "\t file"+"\t"+item.length()/1024);
                                }
                            }
                        }

                        System.out.println("Введите вперед, назад для перемещения. Введите стоп чтобы закончить:");
                        next = in.nextLine();
                        if (next.equals("вперед")) {
                            dir = f;
                            name = in.nextLine();
                            f = new File(dir, name);
                        }
                        if (next.equals("назад")) {
                            //String par=f.getParentFile().getAbsolutePath();
                            //File f2=new File(par);
                            //dir=f2;
                            // dir=dir.getParentFile();
                            f = new File(f.getParentFile().getAbsolutePath());
                            //name=dir.getName();
                        }

                        if(next.equals("создать папку")){
                            String dnm;
                            System.out.println("Имя папки:");
                            dnm = in.nextLine();
                            File myDir = new File(f.getAbsolutePath()+"\\"+dnm);
                            myDir.mkdir();
                        }

                        if (next.equals("создать файл")) {
                            String fnm;
                            System.out.println("Имя файла:");
                            fnm = in.nextLine();
                            File f1 = new File(f.getAbsolutePath()+"\\" +fnm);
                            if (f1.createNewFile()) {
                                System.out.println("Файл был создан");
                            } else System.out.println("Файл уже существует");
                        }

                        if(next.equals("удалить")){
                            String dfil;
                            System.out.println("Имя файла или папки:");
                            dfil = in.nextLine();
                            File dfl = new File(f.getAbsolutePath()+"\\"+dfil);
                            if(dfl.delete()){
                                System.out.println("Файл был удален");
                            }else System.out.println("Файл не найден");
                        }

                        if(next.equals("переименовать")){
                            String olfile, nefile;
                            System.out.println("Какой файл переименовать?:");
                            olfile = in.nextLine();
                            System.out.println("Новое имя:");
                            nefile = in.nextLine();
                            File ofil = new File(f.getAbsolutePath()+"\\"+olfile);
                            File nfil = new File(f.getAbsolutePath()+"\\"+nefile);
                            if(ofil.renameTo(nfil)){
                                System.out.println("Файл переименован успешно");
                            }else{
                                System.out.println("Файл не был переименован");
                            }
                        }

                        if (next.equals("переместить")){
                            String mfil,nepath;
                            System.out.println("Какой файл переместить?:");
                            mfil = in.nextLine();
                            File movfile = new File(f.getAbsolutePath()+"\\"+mfil);
                            System.out.println("Укажите новый путь:");
                            nepath = in.nextLine();
                            File movf = new File(nepath+"\\"+mfil);
                            if(movfile.renameTo(movf)){
                                System.out.println("Файл перемещен");
                            }else{
                                System.out.println("Файл не был перемещен");
                            }
                        }

                        if (next.equals("читать")){
                            String k;
                            System.out.println("Имя файла:");
                            k = in.nextLine();
                            Scanner sc = new Scanner(new File(f.getAbsolutePath()+"\\" + k));
                            while (sc.hasNextLine()) {
                                String str = sc.nextLine();
                                System.out.println(str);
                            }
                        }
                        if (next.equals("стоп")) {
                            F = false;
                        }
                    }

                    break;
                }
                case "8":{
                    return;
                }


            }
        }
    }
}
