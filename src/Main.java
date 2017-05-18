import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.commons.io.FileUtils;

public class Main {

	static int lookback = 240;

	static int predictionLag = 60;
	static int sampleSize = 300;
	private static int testLength = 1200;
	static Float[][] x = new Float[10000+testLength][lookback];
	static Float[] y = new Float[10000];
	static Float[][] rsi = new Float[10000][lookback];
	static Float[] w = new Float[10000];
	static Float[] p = new Float[10000];

	static File file1 = new File("DATA1.txt");

	private static int totalcounts;

	private static int startPos = ;



	public static void main(String[] args) throws Exception {
		file1.createNewFile();
		LoadData2();
		LoadData4();
		LoadData3();
		addTestData();

		putdatainFile();

	}

	private static void addTestData() throws InterruptedException {
		int counter = totalcounts;
		for (int i = lookback + totalcounts; i < totalcounts+testLength  - lookback; i++) {
			Float[] futurevalues = w;
			int until = 0;
			System.out.println(counter);

			Float[] newArray = Arrays.copyOfRange(futurevalues, i - lookback, i);
			y[counter] = futurevalues[i + predictionLag - 1];

			x[counter] = newArray;
			counter++;

		}
		totalcounts = counter;
		System.out.println("found optimal mix of data" + (totalcounts));
		//TimeUnit.SECONDS.sleep(100);

	}

	private static void LoadData3() throws IOException, InterruptedException {

		int counter = 0;
		int upcounter = 0;
		int epochs = 0;
		int downcounter = 0;
		int nocounter = 0;
		int counter1 = 0;
		int counter2 = 0;
		int counter3 = 0;
		int counter4 = 0;
		int counter5 = 0;
		int counter6 = 0;
		int counter7 = 0;
		int counter8 = 0;
		int counter9 = 0;
		int counter10 = 0;
		int totalCounts = 0;
		Random r = new Random(6);
		float pipSecure = 0.0002f;
		int[] sumCount = new int[10];

		Float[] matches = new Float[10000];

		/*
		 * while (((upcounter < sampleSize || downcounter < sampleSize ||
		 * nocounter < sampleSize) || (upcounter == 0 || downcounter == 0 ||
		 * nocounter == 0) || upcounter + downcounter + nocounter < sampleSize *
		 * 3) || counter < sampleSize * 3)
		 */

		while ((counter1 < sampleSize || counter2 < sampleSize || counter3 < sampleSize || counter4 < sampleSize
				|| counter5 < sampleSize || counter6 < sampleSize || counter7 < sampleSize || counter8 < sampleSize
				|| counter9 < sampleSize || counter10 < sampleSize || counter == 10000) && epochs < 1)

		{
			System.out.println(counter);

			if (epochs == 800) {
				break;

			}
			// pipSecure = Math.abs(r.nextFloat());
			x = new Float[10000+testLength][lookback];
			y = new Float[10000+testLength];
			epochs++;
			upcounter = 0;
			downcounter = 0;
			nocounter = 0;
			counter = 0;
			totalCounts = 0;
			counter1 = 0;
			counter2 = 0;
			counter3 = 0;
			counter4 = 0;
			counter5 = 0;
			counter6 = 0;
			counter7 = 0;
			counter8 = 0;
			counter9 = 0;
			counter10 = 0;

			System.out.println("finding optimal match ");

			for (int i = lookback; i < 10000 - lookback; i++) {
				Float[] futurevalues = w;
				int until = 0;

				Float[] newArray = Arrays.copyOfRange(futurevalues, i - lookback, i);
				Float[] newArray2 = Arrays.copyOfRange(p, i - lookback, i);
				if (newArray[0] == null) {
				}

				// rsi[counter] = newArray2;
				float currentX = newArray[lookback - 1];
				float futureY = futurevalues[i + predictionLag - 1];
				// up movement

				if (futureY - currentX > 0.0f && futureY - currentX < pipSecure && counter1 < sampleSize) {

					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter1++;
					counter++;

				} else if (futureY - currentX > pipSecure && futureY - currentX <= pipSecure * 2
						&& counter2 < sampleSize) {
					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter2++;
					counter++;
				} else if (futureY - currentX > pipSecure * 2 && futureY - currentX <= pipSecure * 3
						&& counter3 < sampleSize) {
					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter3++;
					counter++;
				} else if (futureY - currentX > pipSecure * 3 && futureY - currentX <= pipSecure * 4
						&& counter4 < sampleSize) {
					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter4++;
					counter++;
				} else if (futureY - currentX > pipSecure * 4 && futureY - currentX <= pipSecure * 5
						&& counter5 < sampleSize) {
					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter5++;
					counter++;
				}
				// opposite direction

				else if (futureY - currentX < 0.0f && futureY - currentX >= -pipSecure && counter6 < sampleSize) {

					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter6++;
					counter++;

				}

				else if (futureY - currentX < -pipSecure && futureY - currentX >= -pipSecure * 2
						&& counter7 < sampleSize) {
					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter7++;
					counter++;
				} else if (futureY - currentX < -pipSecure * 2 && futureY - currentX >= -pipSecure * 3
						&& counter8 < sampleSize) {
					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter8++;
					counter++;
				} else if (futureY - currentX < -pipSecure * 3 && futureY - currentX >= -pipSecure * 4
						&& counter9 < sampleSize) {
					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter9++;
					counter++;
				} else if (futureY - currentX < -pipSecure * 4 && futureY - currentX >= -pipSecure * 5
						&& counter10 < sampleSize) {
					y[counter] = futurevalues[i + predictionLag - 1];

					x[counter] = newArray;
					counter10++;
					counter++;
					// System.out.println("added most negative");
				}
				System.out.println(counter1 + " " + counter2 + " " + counter3 + " " + counter4 + " " + counter5 + " "
						+ counter6 + " " + counter7 + " " + counter8 + " " + counter9 + " " + counter10);

				int[] listOfNumbers = { counter1, counter2, counter3, counter4, counter5, counter6, counter7, counter8,
						counter9, counter10 };
				totalcounts = IntStream.of(listOfNumbers).sum();

				// System.out.println("total count is equal to" + totalCounts);

				// System.out.println("pip secure is " + pipSecure);
				/*
				 * if (Math.abs(futureY-currentX) > pipSecure && futureY -
				 * currentX > 0 && upcounter < sampleSize) {
				 *
				 * //y[counter] = 1.0f; y[counter] = futurevalues[i +
				 * predictionLag - 1];
				 *
				 * x[counter] = newArray; counter++; upcounter++; } else if
				 * (Math.abs(futureY-currentX) > pipSecure && futureY - currentX
				 * < 0 && downcounter < sampleSize) {
				 *
				 * //y[counter] = -1.0f; y[counter] = futurevalues[i +
				 * predictionLag - 1];
				 *
				 * x[counter] = newArray; downcounter++; counter++; } else if (
				 * Math.abs(futureY-currentX) < pipSecure && nocounter <
				 * sampleSize) {
				 *
				 * //y[counter] = 0.0f; y[counter] = futurevalues[i +
				 * predictionLag - 1];
				 *
				 * x[counter] = newArray; nocounter++; counter++; }
				 */
				// y[counter] = futurevalues[i + predictionLag - 1];

			}

		}

		System.out.println("found optimal mix of data" + totalcounts);
		System.out.println("pip secure is " + pipSecure);
		System.out.println(counter);

		// TimeUnit.SECONDS.sleep(100);

	}

	private static int sumAll(int... numbers) {

		int result = 0;
		for (int i = 0; i < numbers.length; i++) {
			result += numbers[i];
		}
		return result;
	}

	private static void putdatainFile() throws IOException, InterruptedException {

		for (int g = 0; g < lookback; g++) {
			int counta = g + 1;
			FileUtils.writeStringToFile(file1, "x" + counta + ",; ", true);

		}

		FileUtils.writeStringToFile(file1, "y" + ",; \n", true);

		int count = 0;
		for (int i = 0; i < totalcounts; i++) {
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
				System.out.println(i);
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
				/*
				 * Float fuckrsi = rsi[i][u]; String rs =
				 * Float.toString(fuckrsi);
				 *
				 * String[] fuckrsi3 = rs.split("\\."); String shitrsi = ""; for
				 * (int r = 0; r < fuck3.length + 1; r++) { if (r == 0) {
				 * shitrsi = shitrsi + fuckrsi3[0]; } if (r == 1) { shitrsi =
				 * shitrsi + ","; } else if (r == fuckrsi3.length) { shitrsi =
				 * shitrsi + fuckrsi3[r - 1]; break; }
				 *
				 * }
				 */
				// System.out.println(shitrsi);
				FileUtils.writeStringToFile(file1, shit + ";", true);
				// if (u >= (lookback / 2) - 1) {
				// FileUtils.writeStringToFile(file1, shitrsi + ";", true);
				// }

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

		File file1 = new File("5Years.txt");
		int count = 0;
		int counter1 = 0;
		int counter2 = 0;
		Scanner input1 = new Scanner(file1);

		while(counter1 < startPos ) {
			input1.nextLine();
			counter1++;

		}

		while (count < 10000) {

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
			if (counter2 == 10000) {
				return;
			}

			Float u = input1.nextFloat();
			p[counter2] = u;
			counter2++;

		}

	}

}
