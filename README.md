# t2.g2.-Jenkins-shared-library
This repo will store the shared library for our Jenkins pipelines

**Step 1:**
On GitHub, create a shared library repository that will be store the Jenkinsfiles that Jenkins will need access to. Github > Repositories > New > Name and configure the repository.

**Step 2:**
On Jenkins, configure a Global Pipeline Library. Jenkins dashboard > Manage Jenkins > Configure System > scroll down to Global Pipeline Libraries > Assign a name to the library and configure > For the project repository, enter the URL of the GitHub repo created for the shared library in Step 1.

**Step 3:**
Grow the shared library by cloning the repo and adding (.groovy) scripts which contain Jenkinsfiles that would be shared by multiple applications. 

**Sample groovy script in Jenkins shared library**
def call(String repoUrl){
  echo "git clone $repoUrl"     # This line can be replaced with any Jenkinsfile. E.g. pipeline {}
 }

**Step 4:**
For an application to reference a particular Jenkinsfile in the shared library, the Jenkinsfile in the application repository would be created as follows:

**Sample Jenkinsfile in the Application Repo**
@Library ('t2.g2.-shared-library') _
chidanyApp 'https://github.com/etechapp/T2.G2.-AppOne'

**'t2.g2.-shared-library':** represents the name of the library that was configured on Jenkins in Step 2 above.
**chidanyApp:** represents the name of the groovy script in the shared library repo which is being referenced for the application build.
**GitHub Repo URL:** represents the repo URL of the application which is being built

