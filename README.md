``` Java
InputStream input = new FileInputStream(new File("config.yaml"));
Representer representer = new Representer();
representer.getPropertyUtils().setSkipMissingProperties(true);
Yaml yaml = new Yaml(new StubConfigConstructor(), representer);
StubConfig sc = yaml.loadAs(input, StubConfig.class);
sc.initServices();
```