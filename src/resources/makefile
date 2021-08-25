SOURCE = ../main/
OUTPUT = ../../out/
MANIFEST = ./manifest.mf
JARFILE = Platoon.jar

jar_exe:
	javac -cp $(SOURCE) $(SOURCE)*.java -d $(OUTPUT)
	jar cvfm $(JARFILE) $(MANIFEST) -C $(OUTPUT) .

jar:
	java -jar $(JARFILE) $(numTruck) $(duration)

clean:
	rm -rf $(OUTPUT) $(JARFILE) *.dat
