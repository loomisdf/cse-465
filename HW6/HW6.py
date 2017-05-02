# This function should behave in a manner similar
# to the C# version.
def transformIf(items, F, P):
	# fill this in
	None

L1 = [-3, -2, -1, 0, 1, 2, 3]
transformIf(L1, lambda x : x * x, lambda x : x < 0)
print(L1)

L2 = ["hello", "world", "abc"]
transformIf(L2, lambda x : x[0:3], lambda x : len(x) > 3)
print(L2)
