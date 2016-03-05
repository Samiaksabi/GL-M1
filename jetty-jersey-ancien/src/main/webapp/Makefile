
GOAL = test

build:
	ocamlfind ocamlc -package js_of_ocaml -package js_of_ocaml.syntax \
          -syntax camlp4o -linkpkg -o $(GOAL).byte $(GOAL).ml
	js_of_ocaml $(GOAL).byte

clean:
	rm $(GOAL).byte
	rm $(GOAL).cmi
	rm $(GOAL).cmo

all: build clean
