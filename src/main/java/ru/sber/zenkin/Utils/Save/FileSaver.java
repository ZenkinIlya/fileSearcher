package ru.sber.zenkin.Utils.Save;

import ru.sber.zenkin.Entity.FileRecord;

import java.util.TreeSet;

public interface FileSaver {
    void saveListInFile(TreeSet<FileRecord> treeSet, String pathFile);
}
