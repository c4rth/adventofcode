#!/bin/bash

# Loop through all directories and subdirectories
find . -type d | while read dir; do
    # Check if 'input.txt' exists in the current directory
    if [ -f "$dir/input.txt" ]; then
        mv "$dir/input.txt" "$dir/puzzle.txt"
        echo "Renamed $dir/input.txt to $dir/puzzle.txt"
    fi

    # Check if 'test.txt' exists in the current directory
    if [ -f "$dir/test.txt" ]; then
        mv "$dir/test.txt" "$dir/sample.txt"
        echo "Renamed $dir/test.txt to $dir/sample.txt"
    fi

    # Rename any files matching 'test-*.txt' to 'sample-*.txt'
    for test_file in "$dir"/test-*.txt; do
        # Check if the file exists to avoid errors when no matches are found
        if [ -f "$test_file" ]; then
            # Extract the number suffix, if any, and rename accordingly
            suffix="${test_file##*-}"
            new_file="$dir/sample-${suffix}"
            mv "$test_file" "$new_file"
            echo "Renamed $test_file to $new_file"
        fi
    done
done