# Teste de componentes

# ToDo

- [ ] Testar Pixy;
- [x] Testar ultrassônico e infravermelho;
- [ ] Testar encoder do motor;
- [ ] Testar NavX;
	- [x] USB;
	- [ ] I2C;
- [ ] Testar duas webcams;


## Testar Pixy

- https://github.com/PseudoResonance/Pixy2JavaAPI/wiki/Wiring-the-Pixy2-to-a-RoboRIO
- https://docs.pixycam.com/wiki/doku.php?id=wiki:v2:porting_guide
- https://andymark-weblinc.netdna-ssl.com/media/W1siZiIsIjIwMTkvMDcvMTIvMTQvMjUvNDgvOTc0MGNkOGItMmRlOS00Mjc4LWFlYzEtMmY0ZGUyMGI1Y2NhL1BpeHlDYW0gU1BJIEluc3RydWN0aW9ucy5wZGYiXV0/PixyCam%20SPI%20Instructions.pdf?sha=e16096fe11ed8b61

### Wiring Pixy

https://github.com/PseudoResonance/Pixy2JavaAPI/wiki/Wiring-the-Pixy2-to-a-RoboRIO

### Pixy I2C
https://bitbucket.org/Zelda_Programming/bagelbots-frc702-2016/src/7b59b8264d5f7bd92b96b963d95ff9a6d8643000/2016%20FIRST%20Stronghold%20Robot%20Code/src/org/usfirst/frc/team702/robot/PixyCmu5.java?at=master&fileviewer=file-view-default

## Testar encoder do motor;

Descobrir como utilizar o encoder do motor Neverest Gearmotor.
https://github.com/wpilibsuite/allwpilib/blob/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/encoder/Robot.java

Olhe o exemplo do encoder. Alguns parâmetros devem ser alterados para medir corretamente.
Teste primeiro com estes valores, algum valor é mostrado no shuffleboard?

## Testar NavX

A NavX está funcionando corretamente no USB, mas o ideal é utilizar ela na I2C para deixar o USB livres.
Tente conectar na expansão MXP da RoboRIo conforme o tutorial (cuidado com as entradas GNC e 5V):
https://pdocs.kauailabs.com/navx-micro/installation/roborio-installation/

Para testar use este código: https://github.com/team6902/tests/tree/master/navx_i2c_0301 e abra o __ShuffleBoard__ para ver se os dados são atualizados.

### Outros exemplos

https://pdocs.kauailabs.com/navx-micro/

## Testar duas webcans

Colocar duas webcams e verificar se aparece no __ShuffleBoard__.
Utilizar o código: https://github.com/team6902/tests/tree/master/cameraserver_1101 

