TARGETFOLDER=../backend/src/main/resources

rm -rf $TARGETFOLDER
rm -rf build
mkdir $TARGETFOLDER

npm run build

cp -r build $TARGETFOLDER