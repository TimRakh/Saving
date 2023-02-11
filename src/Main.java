import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress pl1 = new GameProgress(67, 4, 9, 2.14);
        GameProgress pl2 = new GameProgress(81, 9, 61, 9.23);
        GameProgress pl3 = new GameProgress(99, 2, 2, 0.9);

        saveGame("D://Games//savegames//save.dat", pl1);
        saveGame("D://Games//savegames//save1.dat", pl2);
        saveGame("D://Games//savegames//save2.dat", pl3);

        ArrayList<String> list = new ArrayList<>();
        list.add("D://Games//savegames//save.dat");
        list.add("D://Games//savegames//save1.dat");
        list.add("D://Games//savegames//save2.dat");
        zipFiles("D://Games//savegames//zip.zip", list);
        File game1Dat = new File("D://Games//savegames//save.dat.dat");
        File game2Dat = new File("D://Games//savegames//save1.dat");
        File game3Dat = new File("D://Games//savegames//save2.dat");
        if (game1Dat.delete()) System.out.println("Файл \"save.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"save1.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"save2.dat\" удален");
    }

    public static void saveGame(String adress, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(adress);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void zipFiles(String path, ArrayList<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
