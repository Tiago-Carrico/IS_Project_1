import subprocess

# Run the other script
#Initial value, max + step, step
for i in range(100000, 5100000, 100000):
    subprocess.run(["python", "is1\\DataGenerator\\DataGenerator.py", str(i)], shell=True)


#100000, 5000000, 100000