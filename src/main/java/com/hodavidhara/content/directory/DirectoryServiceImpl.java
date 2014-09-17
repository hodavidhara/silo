package com.hodavidhara.content.directory;

import com.hodavidhara.elasticsearch.ElasticSearchDao;
import com.hodavidhara.elasticsearch.Type;
import com.hodavidhara.filesystem.FileSystemService;
import com.hodavidhara.filesystem.PathUtil;
import com.hodavidhara.util.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DirectoryServiceImpl implements DirectoryService {

    @Autowired
    private ElasticSearchDao elasticSearchDao;
    @Autowired
    private FileSystemService fileSystemService;

    @Override
    public Directory createDirectory(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }

        String parentPath = PathUtil.getParent(path);
        if (parentPath == null) {
            return null;
        }

        boolean parentDirectoryDoesNotExist = getDirectoryByPath(parentPath) == null;
        if (parentDirectoryDoesNotExist) {
            createDirectory(parentPath);
        }

        fileSystemService.createDirectory(path);

        Directory directory = new Directory();
        directory.setPath(path);
        directory.setName(PathUtil.getFilename(path));
        return elasticSearchDao.create(directory, Type.DIRECTORY);
    }

    @Override
    public Directory getDirectoryByPath(String path) {
        return elasticSearchDao.searchSingle(Queries.queryByPath(path, Type.DIRECTORY));
    }

    @Override
    public Directory getDirectoryById(String id) {
        return elasticSearchDao.read(id, Type.DIRECTORY);
    }
}
