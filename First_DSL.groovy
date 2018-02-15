def gitUrl = 'git://github.com/erramsai/Shiva_M.git'

job('PROJ-test1-job1') {
    scm {
        git(gitUrl)
    }
    triggers {
        scm('*/15 * * * *')
    }
    steps {
        maven('-e clean compile test')
    }
}

job('PROJ-test-Job2') {
    scm {
        git(gitUrl)
    }
    triggers {
        cron('15 13 * * *')
    }
    steps {
        maven('package')
    }
}

job('PROJ-test-job3') {
    scm {
        git(gitUrl)
    }
    triggers {
        cron('15 1,13 * * *')
    }
    steps {
        maven('-e clean install')
    }
}
}