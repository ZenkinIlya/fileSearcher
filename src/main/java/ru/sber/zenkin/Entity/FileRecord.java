package ru.sber.zenkin.Entity;

import java.util.Objects;

public class FileRecord implements Comparable<FileRecord>{

    private String pathFile;
    private String date;
    //тип long для того чтобы хранить размер больше 2Гб
    //также attributes.size() возвращает тип long
    private long size;

    public FileRecord(String pathFile, String date, long size) {
        this.pathFile = pathFile;
        this.date = date;
        this.size = size;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public int compareTo(FileRecord o) {
        //Сравнение по пути к файлу + имя файла
        int result = this.pathFile.compareTo(o.pathFile);

        //Дальнейшее сравнение не обязательно было писать, так как путь + имя у каждого файла уникальный

        //Сравнение по дате создания/изменения
        if (result == 0){
            result = this.date.compareTo(o.date);
        }
        //Сравнение по размеру
        if (result == 0){
            if (this.size < o.size){
                result = -1;
            }else if (this.size > o.size){
                result = 1;
            }
        }
        return result;
    }

    //Подсчет хеш-кода текущего объекта
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pathFile == null) ? 0 : pathFile.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = (int) (prime * result + size);
        return result;
    }

    //Сравнение экземпляров класса FileRecord
    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return false;
        }
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }

        FileRecord fileRecord = (FileRecord) obj;
        return (Objects.equals(pathFile, fileRecord.pathFile)) &&
                (Objects.equals(date, fileRecord.date)) &&
                size == fileRecord.size;
    }
}
