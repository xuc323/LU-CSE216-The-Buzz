TARGETFOLDER=../backend/src/main/resources

rm -rf $TARGETFOLDER
mkdir $TARGETFOLDER

npm run build

cp -r build $TARGETFOLDER