SOURCE = ../main/
OUTPUT = ../../out/
MANIFEST = ./MANIFEST.MF
JARFILE = Platoon.jar

jar_exe:
	javac -cp $(SOURCE) $(SOURCE)*.java -d $(OUTPUT)
	jar cvfm $(JARFILE) $(MANIFEST) -C $(OUTPUT) .

jar:
	java -jar $(JARFILE)

clean:
	rm -rf $(OUTPUT) $(JARFILE)


