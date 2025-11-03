package com.mertalptekin.springbootrestapp.springContext.commit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommitService {


    private final ICommit commit;

    public CommitService(@Qualifier("dbCommit") ICommit commit) {
        this.commit = commit;
    }


    public void save() {
        commit.commitChanges();
    }


}
