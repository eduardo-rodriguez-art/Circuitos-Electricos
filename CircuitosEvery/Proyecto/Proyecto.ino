float corriente = 0.0;  // Valor de la corriente que será calculado
float resistencia=1000;  //Valor de la resistencia, pasada por valor

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);  //inicializa el puerto serial
}

void loop() {
  // put your main code here, to run repeatedly:
  for(int i=0;i<=20;i++){
    corriente = (float) analogRead(0)*(5/1023.0)/resistencia+corriente;
  }

  Serial.print((corriente*1000)/20.3);
  Serial.print(" mA");
  delay(30);

  corriente = 0; 
}
