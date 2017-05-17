import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public class Main {

	static int lookback = 60;

	static int predictionLag = 15;
	static int sampleSize = 500;

	static Float[][] x = new Float[3400][lookback];
	static Float[] y = new Float[3400];
	static Float[][] rsi = new Float[3400][lookback];
	static Float[] w = new Float[3400];
	static Float[] p = new Float[3400];

	static File file1 = new File("DATA1.txt");

	public static void main(String[] args) throws Exception {
		file1.createNewFile();
		LoadData2();
		LoadData4();
		LoadData3();

		putdatainFile();

	}

	private static void LoadData3() throws IOException, InterruptedException {

		int counter = 0;
		int upcounter = 0;
		int downcounter = 0;
		int nocounter = 0;
		Random r = new Random(6);
		float pipSecure = 0;
		Float[] matches = new Float[3400];

		while (((upcounter < sampleSize || downcounter < sampleSize || nocounter < sampleSize)
				|| (upcounter == 0 || downcounter == 0 || nocounter == 0)
				|| upcounter + downcounter + nocounter < sampleSize * 3) || counter < sampleSize+sampleSize+sampleSize) {
			pipSecure = Math.abs(r.nextFloat());
			x = new Float[3400][lookback];
			y = new Float[3400];
			upcounter = 0;
			downcounter = 0;
			nocounter = 0;
			counter = 0;
			System.out.println("finding optimal match with pipSecure of " + pipSecure);

			for (int i = lookback; i < 3400 - lookback; i++) {
				Float[] futurevalues = w;
				int until = 0;

				Float[] newArray = Arrays.copyOfRange(futurevalues, i - lookback, i);
				Float[] newArray2 = Arrays.copyOfRange(p, i - lookback, i);
				if (newArray[0] == null) {
				}

				rsi[counter] = newArray2;
				float currentX = newArray[lookback - 1];
				float futureY = futurevalues[i + predictionLag - 1];

				if (Math.abs(currentX - futureY) > pipSecure && futureY - currentX > 0 && upcounter < sampleSize) {

					y[counter] = 1.0f;

					x[counter] = newArray;
					counter++;
					upcounter++;
				} else if (Math.abs(currentX - futureY) > pipSecure && futureY - currentX < 0
						&& downcounter < sampleSize) {

					y[counter] = -1.0f;

					x[counter] = newArray;
					downcounter++;
					counter++;
				} else if ( Math.abs(currentX - futureY) < pipSecure && nocounter < sampleSize) {

					y[counter] = 0.0f;

					x[counter] = newArray;
					nocounter++;
					counter++;
				} else {
					//y[counter] = null;

					//x[counter] = null;
					//counter++;

				}

				// y[counter] = futurevalues[i + predictionLag - 1];

			}



		}

		System.out.println("found optimal mix of data" + " " + downcounter + " " + upcounter + " " + nocounter);
		System.out.println("pip secure is " + pipSecure);
		System.out.println(counter);
	    //TimeUnit.SECONDS.sleep(100);

	}

	private static void putdatainFile() throws IOException, InterruptedException {

		for (int g = 0; g < lookback * 1.5; g++) {
			int counta = g + 1;
			FileUtils.writeStringToFile(file1, "x" + counta + ",; ", true);

		}

		FileUtils.writeStringToFile(file1, "y" + ",; \n", true);

		int count = 0;
		for (int i = 0; i < sampleSize*3; i++) {
			System.out.println("creating datafile");
			for (int u = 0; u < lookback; u++) {
				// System.out.println(u);
				Float fuck = 0.0f;
				try {
					fuck = x[i][u];
				} catch (NullPointerException e) {
					System.out.println("caught null x");
					TimeUnit.SECONDS.sleep(100);
					break;
				}
				String x = Float.toString(fuck);

				String[] fuck3 = x.split("\\.");
				String shit = "";
				for (int o = 0; o < fuck3.length + 1; o++) {
					if (o == 0) {
						shit = shit + fuck3[0];
					}
					if (o == 1) {
						shit = shit + ",";
					} else if (o == fuck3.length) {
						shit = shit + fuck3[o - 1];
						break;
					}
				}
				Float fuckrsi = rsi[i][u];
				String rs = Float.toString(fuckrsi);

				String[] fuckrsi3 = rs.split("\\.");
				String shitrsi = "";
				for (int r = 0; r < fuck3.length + 1; r++) {
					if (r == 0) {
						shitrsi = shitrsi + fuckrsi3[0];
					}
					if (r == 1) {
						shitrsi = shitrsi + ",";
					} else if (r == fuckrsi3.length) {
						shitrsi = shitrsi + fuckrsi3[r - 1];
						break;
					}

				}
				// System.out.println(shitrsi);
				FileUtils.writeStringToFile(file1, shit + ";", true);
				//if (u >= (lookback / 2) - 1) {
					//FileUtils.writeStringToFile(file1, shitrsi + ";", true);
				//}

				if (u == lookback - 1) {
					Float fuck4 = 0.0f;
					try {
						fuck4 = y[i];
					} catch (NullPointerException e) {
						System.out.println("caught null y");
						TimeUnit.SECONDS.sleep(100);
						break;
					}

					String y = Float.toString(fuck4);
					String[] fucky = y.split("\\.");
					String shit2 = "";
					for (int o = 0; o < fucky.length + 1; o++) {
						if (o == 0) {
							shit2 = shit2 + fucky[0];
						}
						if (o == 1) {
							shit2 = shit2 + ",";
						} else if (o == fucky.length) {
							shit2 = shit2 + fucky[o - 1];
							break;
						}
					}

					FileUtils.writeStringToFile(file1, shit2 + "; \n ", true);
					count++;

				}

			}
			count = 0;
		}
		System.out.println("file created");

	}

	private static void LoadData2() throws FileNotFoundException {

		File file1 = new File("data.txt");
		int count = 0;
		int counter1 = 0;
		int counter2 = 0;
		Scanner input1 = new Scanner(file1);

		while (count < 3400) {

			String u = input1.nextLine();
			String[] arr = u.split(",");
			String yx = arr[0];
			Float yfloat = Float.parseFloat(yx);
			w[count] = yfloat.floatValue();
			count++;
		}

	}

	private static void LoadData4() throws FileNotFoundException {

		File file1 = new File("rsi1.txt");
		int count = 0;
		int counter1 = 0;
		int counter2 = 0;
		Scanner input1 = new Scanner(file1);

		while (input1.hasNext()) {
			if (counter2 == 3400) {
				return;
			}

			Float u = input1.nextFloat();
			p[counter2] = u;
			counter2++;

		}

	}

}
